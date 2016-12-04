package dk.fitfit.liftlog.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import dk.fitfit.liftlog.domain.WorkoutSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WorkoutSetResource extends ResourceObject {
	private int repetition;
	private double weight;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timestamp;
	private ExerciseResource exercise;

	public WorkoutSetResource() {
	}

	private WorkoutSetResource(WorkoutSet workoutSet) {
		this.repetition = workoutSet.getRepetition();
		this.weight = workoutSet.getWeight();
		this.timestamp = workoutSet.getTimestamp();
		this.exercise = ExerciseResource.from(workoutSet.getExercise());
	}

	public static List<WorkoutSetResource> from(Set<WorkoutSet> sets) {
		List<WorkoutSetResource> resources = new ArrayList<>();
		for (WorkoutSet set : sets) {
			WorkoutSetResource resource = WorkoutSetResource.from(set);
			resources.add(resource);
		}
		return resources;
	}

	public static WorkoutSetResource from(WorkoutSet workoutSet) {
		return new WorkoutSetResource(workoutSet);
	}

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public ExerciseResource getExercise() {
		return exercise;
	}

	public void setExercise(ExerciseResource exercise) {
		this.exercise = exercise;
	}
}
