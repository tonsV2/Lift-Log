package dk.fitfit.liftlog.resource.assembler;

import dk.fitfit.liftlog.controller.ExerciseController;
import dk.fitfit.liftlog.domain.Exercise;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ExerciseLinksAssembler implements ResourceLinksAssemblerInterface<Exercise> {
	@Override
	public List<Link> getLinks(Exercise entity) {
		List<Link> links = new ArrayList<>();
		// TODO: Is this "bad" hateoas? Shouldn't self be pointing to the prototype and then the resource should also have a save rel?
		if (isPrototype(entity)) {
			links.add(linkTo(methodOn(ExerciseController.class).getExercises()).withSelfRel());
		} else {
			links.add(linkTo(methodOn(ExerciseController.class).getExercise(entity.getId())).withSelfRel());
		}
		return links;
	}

	public List<Link> getContainerLinks() {
		List<Link> links = new ArrayList<>();
		links.add(linkTo(methodOn(ExerciseController.class).getExercises()).withSelfRel());
		links.add(linkTo(methodOn(ExerciseController.class).getPrototype()).withRel("prototype"));
		return links;
	}

	private boolean isPrototype(Exercise exercise) {
		return exercise.getId() == null;
	}
}
