package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.Session;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.resource.SessionResource;
import dk.fitfit.liftlog.security.CurrentUserHolder;
import dk.fitfit.liftlog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class HelloWorld {
	private final CurrentUserHolder currentUserHolder;
	private final MapperService mapperService;
	private final WorkoutSetServiceInterface workoutSetService;
	private final ExerciseServiceInterface exerciseService;
	private final SessionServiceInterface sessionService;

	@Autowired
	public HelloWorld(CurrentUserHolder currentUserHolder, MapperService mapperService, WorkoutSetServiceInterface workoutSetService, ExerciseServiceInterface exerciseService, SessionServiceInterface sessionService) {
		this.currentUserHolder = currentUserHolder;
		this.mapperService = mapperService;
		this.workoutSetService = workoutSetService;
		this.exerciseService = exerciseService;
		this.sessionService = sessionService;
	}

	@GetMapping("/init")
	public List<SessionResource> init() {
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

		Iterable<Session> sessions = sessionService.findAll(user);
		// TODO: Why are sets null??? But not when requested from the SessionController?
		return SessionResource.from(sessions);
	}

}
