package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.resource.ExerciseResource;
import dk.fitfit.liftlog.service.ExerciseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ExerciseController {
	private final ExerciseServiceInterface exerciseService;

	@Autowired
	public ExerciseController(ExerciseServiceInterface exerciseService) {
		this.exerciseService = exerciseService;
	}

//	@GetMapping("/exercises")
	@RequestMapping(value = "/exercises", method = RequestMethod.GET)
// TODO: This will look really ugly when further wrapped in a ResponseEntity... return type ResponseEntity<Resources<Resource<ExerciseResource>>>
	public List<ExerciseResource> getExercises() {
		Iterable<Exercise> exercises = exerciseService.findAll();
		List<ExerciseResource> resources = new ArrayList<>();
		for (Exercise exercise : exercises) {
			ExerciseResource resource = ExerciseResource.from(exercise);
			Link selfLink = linkTo(methodOn(ExerciseController.class).getExercise(exercise.getId())).withSelfRel();
			resource.add(selfLink);
			resources.add(resource);
		}
//		Iterable<ExerciseResource> resources = ExerciseResource.from(exercises);
		// TODO: Somehow add links to the individual resources here...
//		Resources<Resource<ExerciseResource>> wrappedResources = Resources.wrap(resources);
//		Resources<ExerciseResource> wrappedResources = new Resources<>(resources);
		// TODO: Somehow add links to the collection of resources here...
//		Link selfLink = linkTo(methodOn(ExerciseController.class).getExercises()).withSelfRel();
//		wrappedResources.add(selfLink);
		return resources;
//		return Resources.wrap(resources);
	}

//	@GetMapping("/exercises/{id}")
	@RequestMapping(value = "/exercises/{id}", method = RequestMethod.GET)
	public ExerciseResource getExercise(@PathVariable long id) {
		Exercise exercise = exerciseService.findOne(id);
		ExerciseResource resource = ExerciseResource.from(exercise);
		// TODO: Should this really be done here and if so, like done below?
		Link selfLink = linkTo(methodOn(ExerciseController.class).getExercise(exercise.getId())).withSelfRel();
		resource.add(selfLink);
		return resource;
	}
}
