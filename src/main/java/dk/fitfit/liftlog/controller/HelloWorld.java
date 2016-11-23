package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.service.ExerciseService;
import dk.fitfit.liftlog.service.WorkoutSetService;
import dk.fitfit.liftlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWorld {
	private final WorkoutSetService workoutSetService;
	private final ExerciseService exerciseService;
	private final UserService userService;

	@Autowired
	public HelloWorld(WorkoutSetService workoutSetService, ExerciseService exerciseService, UserService userService) {
		this.workoutSetService = workoutSetService;
		this.exerciseService = exerciseService;
		this.userService = userService;
	}

	@GetMapping("/sets")
	public Iterable<WorkoutSet> sets() {
		return workoutSetService.findAll();
	}

	@GetMapping("/init")
	public Iterable<WorkoutSet> init() {
		User user = new User("tons", "tons@tons.dk");
		userService.save(user);

		Exercise bp = new Exercise("Bench press", "Pecks, baby!");
		exerciseService.save(bp);
		Exercise squat = new Exercise("Squat", "Never skip leg day!");
		exerciseService.save(squat);

		WorkoutSet workoutSet = new WorkoutSet();
		workoutSet.setRepetition(10);
		workoutSet.setWight(70D);

		workoutSetService.log(user, bp, 10, 70D, LocalDateTime.now().minusDays(1));
		workoutSetService.log(user, squat, 8, 70D, LocalDateTime.now().minusDays(1));

		workoutSetService.log(user, bp, workoutSet);
		workoutSetService.log(user, squat, 8, 70D);

		workoutSetService.log(user, bp, 10, 70D, LocalDateTime.now().plusDays(1));
		workoutSetService.log(user, squat, 8, 70D, LocalDateTime.now().plusDays(1));

		return workoutSetService.findAll();
	}

}
