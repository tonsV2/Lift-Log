package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.resource.WorkoutSetResource;
import dk.fitfit.liftlog.service.ExerciseService;
import dk.fitfit.liftlog.service.MapperService;
import dk.fitfit.liftlog.service.UserService;
import dk.fitfit.liftlog.service.WorkoutSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkoutSetController {
	private final MapperService mapperService;
	private final WorkoutSetService workoutSetService;
	private final UserService userService;
	private final ExerciseService exerciseService;

	@Autowired
	public WorkoutSetController(MapperService mapperService, UserService userService, WorkoutSetService workoutSetService, ExerciseService exerciseService) {
		this.mapperService = mapperService;
		this.userService = userService;
		this.workoutSetService = workoutSetService;
		this.exerciseService = exerciseService;
	}

	@PostMapping("/sets/prototype/{exerciseId}")
	public WorkoutSetResource prototype(@PathVariable long exerciseId) {
		WorkoutSet workoutSet = new WorkoutSet();
		User user = userService.findOne(1);
		workoutSet.setUser(user);
		Exercise exercise = exerciseService.findOne(exerciseId);
		// TODO: Throw 404 if exercise == null
		workoutSet.setExercise(exercise);
		return mapperService.map(workoutSet);
	}

	@PostMapping("/sets")
	public WorkoutSetResource save(@RequestBody WorkoutSetResource workoutSetResource) {
		WorkoutSet workoutSet = mapperService.map(workoutSetResource);
		// TODO: Find logged in user... or throw 401
		User user = userService.findOne(1);
		workoutSet.setUser(user);
		WorkoutSet saved = workoutSetService.save(workoutSet);
		return mapperService.map(saved);
	}

	@GetMapping("/sets")
	public Iterable<WorkoutSet> sets() {
		return workoutSetService.findAll();
	}
}
