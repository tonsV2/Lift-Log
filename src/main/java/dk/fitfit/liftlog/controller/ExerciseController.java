package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.resource.ExerciseResource;
import dk.fitfit.liftlog.resource.WorkoutSetResource;
import dk.fitfit.liftlog.service.ExerciseService;
import dk.fitfit.liftlog.service.MapperService;
import dk.fitfit.liftlog.service.WorkoutSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExerciseController {
	private final MapperService mapperService;
	private final ExerciseService exerciseService;

	@Autowired
	public ExerciseController(MapperService mapperService, ExerciseService exerciseService) {
		this.mapperService = mapperService;
		this.exerciseService = exerciseService;
	}

	@GetMapping("/exercises")
	public Iterable<ExerciseResource> getExercises() {
		Iterable<Exercise> exercises = exerciseService.findAll();
		return mapperService.map(exercises);
	}

	@GetMapping("/exercises/{id}")
	public ExerciseResource getExercise(@PathVariable long id) {
		Exercise exercise = exerciseService.findOne(id);
		return mapperService.map(exercise);
	}
}
