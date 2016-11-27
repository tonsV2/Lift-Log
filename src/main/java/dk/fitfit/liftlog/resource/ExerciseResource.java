package dk.fitfit.liftlog.resource;

import dk.fitfit.liftlog.domain.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseResource extends ResourceObject {
	private String name;
	private String description;

	private ExerciseResource(Exercise exercise) {
		this.name = exercise.getName();
		this.description = exercise.getDescription();
	}

	public static ExerciseResource from(Exercise exercise) {
		return new ExerciseResource(exercise);
	}

	public static Iterable<ExerciseResource> from(Iterable<Exercise> exercises) {
		List<ExerciseResource> resources = new ArrayList<>();
		for (Exercise exercise : exercises) {
			resources.add(ExerciseResource.from(exercise));
		}
		return resources;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
