package dk.fitfit.liftlog.resource;

import com.google.common.collect.Streams;
import dk.fitfit.liftlog.domain.Exercise;

import java.util.stream.Collectors;

public class ExerciseResource extends ResourceObject {
	private String name;
	private String description;

	public ExerciseResource() {
	}

	private ExerciseResource(Exercise exercise) {
		this.name = exercise.getName();
		this.description = exercise.getDescription();
	}

	public static ExerciseResource from(Exercise exercise) {
		return new ExerciseResource(exercise);
	}

	public static Iterable<ExerciseResource> from(Iterable<Exercise> exercises) {
		return Streams.stream(exercises)
				.map(ExerciseResource::from)
				.collect(Collectors.toList());
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
