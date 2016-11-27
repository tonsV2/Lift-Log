package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.resource.ExerciseResource;
import dk.fitfit.liftlog.service.ExerciseService;
import dk.fitfit.liftlog.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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
	// TODO: This will look really ugly when further wrapped in a ResponseEntity... return type ResponseEntity<Resources<Resource<ExerciseResource>>>
	public Resources<Resource<ExerciseResource>> getExercises() {
		Iterable<Exercise> exercises = exerciseService.findAll();
		Iterable<ExerciseResource> resources = ExerciseResource.from(exercises);
		// TODO: Somehow add links to the individual resources here...
		Resources<Resource<ExerciseResource>> wrappedResources = Resources.wrap(resources);
		// TODO: Somehow add links to the collection of resources here...
		return wrappedResources;
	}

	@GetMapping("/exercises/{id}")
	public ExerciseResource getExercise(@PathVariable long id) {
		Exercise exercise = exerciseService.findOne(id);
		ExerciseResource resource = ExerciseResource.from(exercise);
		// TODO: Avoid the hardcoded string "exercises"
		// TODO: Should this really be done here and if so, like done below?
		Link selfLink = linkTo(ExerciseController.class).slash("exercises").slash(exercise.getId()).withSelfRel();
		resource.add(selfLink);
		return resource;
	}
}
