package dk.fitfit.liftlog.resource.assembler;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.resource.ExerciseResource;
import dk.fitfit.liftlog.resource.ResourceContainer;
import dk.fitfit.liftlog.resource.mapper.ExerciseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExerciseAssembler {
	private final ExerciseLinksAssembler linksAssembler;
	private final ExerciseMapper exerciseMapper;

	@Autowired
	public ExerciseAssembler(ExerciseLinksAssembler linksAssembler, ExerciseMapper exerciseMapper) {
		this.linksAssembler = linksAssembler;
		this.exerciseMapper = exerciseMapper;
	}

	public ExerciseResource toResource(Exercise exercise) {
		List<Link> links = linksAssembler.getLinks(exercise);
		//ExerciseResource resource = ExerciseResource.from(exercise);
		ExerciseResource resource = exerciseMapper.map(exercise);
		resource.add(links);
		return resource;
	}

	public ResourceContainer<ExerciseResource> toResources(Iterable<Exercise> exercises) {
		List<ExerciseResource> resources = new ArrayList<>();
		for (Exercise exercise : exercises) {
			resources.add(toResource(exercise));
		}
		ResourceContainer<ExerciseResource> container = new ResourceContainer<>(resources);
		container.add(linksAssembler.getContainerLinks());
		return container;
	}
}
