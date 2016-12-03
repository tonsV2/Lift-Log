package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.resource.WorkoutSetResource;
import dk.fitfit.liftlog.security.CurrentUserHolder;
import dk.fitfit.liftlog.service.ExerciseServiceInterface;
import dk.fitfit.liftlog.service.MapperService;
import dk.fitfit.liftlog.service.WorkoutSetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkoutSetController {
	private final CurrentUserHolder currentUserHolder;
	private final MapperService mapperService;
	private final WorkoutSetServiceInterface workoutSetService;
	private final ExerciseServiceInterface exerciseService;

	@Autowired
	public WorkoutSetController(CurrentUserHolder currentUserHolder, MapperService mapperService, WorkoutSetServiceInterface workoutSetService, ExerciseServiceInterface exerciseService) {
		this.currentUserHolder = currentUserHolder;
		this.mapperService = mapperService;
		this.workoutSetService = workoutSetService;
		this.exerciseService = exerciseService;
	}

	@PostMapping("/sets/prototype/{exerciseId}")
	public WorkoutSetResource prototype(@PathVariable long exerciseId) {
		WorkoutSet workoutSet = new WorkoutSet();
		User user = currentUserHolder.getUser();
		workoutSet.setUser(user);
		Exercise exercise = exerciseService.findOne(exerciseId);
		// TODO: Throw 404 if exercise == null
		workoutSet.setExercise(exercise);
		return mapperService.map(workoutSet);
	}

	@PostMapping("/sets")
	public WorkoutSetResource save(@RequestBody WorkoutSetResource workoutSetResource) {
		WorkoutSet workoutSet = mapperService.map(workoutSetResource);
		User user = currentUserHolder.getUser();
		WorkoutSet saved = workoutSetService.save(user, workoutSet);
		return mapperService.map(saved);
	}

	@GetMapping("/sets/test")
	public WorkoutSet test() {
		User user = currentUserHolder.getUser();
		return workoutSetService.save(user, new WorkoutSet());
	}

	@GetMapping("/sets")
	public Iterable<WorkoutSet> sets() {
		return workoutSetService.findAll(currentUserHolder.getUser());
	}
}
