package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.resource.WorkoutSetResource;
import dk.fitfit.liftlog.service.MapperService;
import dk.fitfit.liftlog.service.ExerciseService;
import dk.fitfit.liftlog.service.UserService;
import dk.fitfit.liftlog.service.WorkoutSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWorld {
	private final MapperService mapperService;
	private final WorkoutSetService workoutSetService;
	private final ExerciseService exerciseService;
	private final UserService userService;

	@Autowired
	public HelloWorld(MapperService mapperService, WorkoutSetService workoutSetService, ExerciseService exerciseService, UserService userService) {
		this.mapperService = mapperService;
		this.workoutSetService = workoutSetService;
		this.exerciseService = exerciseService;
		this.userService = userService;
	}

	@GetMapping("/init")
	public Iterable<WorkoutSetResource> init() {
		User user = new User("tons", "tons@tons.dk");
		userService.save(user);

		User user2 = new User("snot", "snot@tons.dk");
		userService.save(user2);

		Exercise bp = new Exercise("Bench press", "Pecks, baby!");
		exerciseService.save(bp);
		Exercise squat = new Exercise("Squat", "Never skip leg day!");
		exerciseService.save(squat);
		Exercise deadlift = new Exercise("Deadlift", "Back fucker! Ey, Thomas? ;-)");
		exerciseService.save(deadlift);
		Exercise chinup = new Exercise("Chin-up", "The chin-up (also known as a chin or chinup) is a strength training exercise. People frequently do this exercise with the intention of strengthening muscles such as the latissimus dorsi and biceps, which extend the shoulder and flex the elbow, respectively.");
		exerciseService.save(chinup);

		WorkoutSet workoutSet = new WorkoutSet();
		workoutSet.setRepetition(10);
		workoutSet.setWeight(70D);

		workoutSetService.log(user, bp, 10, 70D, LocalDateTime.now().minusDays(1));
		workoutSetService.log(user, squat, 8, 70D, LocalDateTime.now().minusDays(1));

		workoutSetService.log(user, bp, workoutSet);
		workoutSetService.log(user, squat, 8, 70D);

		workoutSetService.log(user, bp, 10, 70D, LocalDateTime.now().plusDays(1));
		workoutSetService.log(user, squat, 8, 70D, LocalDateTime.now().plusDays(1));

		Iterable<WorkoutSet> workoutSets = workoutSetService.findAll();
		return mapperService.map(workoutSets);
	}

}
