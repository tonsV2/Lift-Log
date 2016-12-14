package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.resource.ExerciseResource;
import dk.fitfit.liftlog.resource.ResourceContainer;
import dk.fitfit.liftlog.resource.assembler.ExerciseAssembler;
import dk.fitfit.liftlog.service.ExerciseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseController {
	private final ExerciseServiceInterface exerciseService;
	private final ExerciseAssembler exerciseAssembler;

	@Autowired
	public ExerciseController(ExerciseServiceInterface exerciseService, ExerciseAssembler exerciseAssembler) {
		this.exerciseService = exerciseService;
		this.exerciseAssembler = exerciseAssembler;
	}

//	@PostMapping("/exercises/prototype
	@RequestMapping(value = "/exercises/prototype", method = RequestMethod.GET)
	public ExerciseResource getPrototype() {
		return exerciseAssembler.toResource(new Exercise());
	}

//	@GetMapping
	@RequestMapping(value = "/exercises", method = RequestMethod.GET)
	public ResourceContainer getExercises() {
		Iterable<Exercise> exercises = exerciseService.findAll();
		return exerciseAssembler.toResources(exercises);
	}

//	@GetMapping("/{id}")
	@RequestMapping(value = "/exercises/{id}", method = RequestMethod.GET)
	public ExerciseResource getExercise(@PathVariable long id) {
		Exercise exercise = exerciseService.findOne(id);
		return exerciseAssembler.toResource(exercise);
	}
}
