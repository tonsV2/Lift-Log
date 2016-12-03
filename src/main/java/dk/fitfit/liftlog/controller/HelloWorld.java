package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.resource.WorkoutSetResource;
import dk.fitfit.liftlog.security.CurrentUserHolder;
import dk.fitfit.liftlog.service.ExerciseServiceInterface;
import dk.fitfit.liftlog.service.MapperService;
import dk.fitfit.liftlog.service.UserServiceInterface;
import dk.fitfit.liftlog.service.WorkoutSetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWorld {
	private final CurrentUserHolder currentUserHolder;
	private final MapperService mapperService;
	private final WorkoutSetServiceInterface workoutSetService;
	private final ExerciseServiceInterface exerciseService;
	private final UserServiceInterface userService;

	@Autowired
	public HelloWorld(CurrentUserHolder currentUserHolder, MapperService mapperService, WorkoutSetServiceInterface workoutSetService, ExerciseServiceInterface exerciseService, UserServiceInterface userService) {
		this.currentUserHolder = currentUserHolder;
		this.mapperService = mapperService;
		this.workoutSetService = workoutSetService;
		this.exerciseService = exerciseService;
		this.userService = userService;
	}

	@GetMapping("/init")
	public Iterable<WorkoutSetResource> init() {
		User user = currentUserHolder.getUser();

		Exercise bp = new Exercise("Bench press", "Pecks, baby!");
		exerciseService.save(bp);
		Exercise squat = new Exercise("Squat", "Never skip leg day!");
		exerciseService.save(squat);
		Exercise deadlift = new Exercise("Deadlift", "Back fucker! Ey, Thomas? ;-)");
		exerciseService.save(deadlift);
		Exercise chinup = new Exercise("Chin-up", "The chin-up (also known as a chin or chinup) is a strength training exercise. People frequently do this exercise with the intention from strengthening muscles such as the latissimus dorsi and biceps, which extend the shoulder and flex the elbow, respectively.");
		exerciseService.save(chinup);

		workoutSetService.log(user, bp, 10, 60D, LocalDateTime.now().minusDays(1));
		workoutSetService.log(user, squat, 8, 70D, LocalDateTime.now().minusDays(1));

		workoutSetService.log(user, bp, 10, 65D);
		workoutSetService.log(user, squat, 8, 75D);

		workoutSetService.log(user, bp, 10, 70D, LocalDateTime.now().plusDays(1));
		workoutSetService.log(user, squat, 8, 80D, LocalDateTime.now().plusDays(1));

		Iterable<WorkoutSet> workoutSets = workoutSetService.findAll(currentUserHolder.getUser());
		return mapperService.map(workoutSets);
	}

}
