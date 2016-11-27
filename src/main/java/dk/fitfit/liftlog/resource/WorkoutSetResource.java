package dk.fitfit.liftlog.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import dk.fitfit.liftlog.domain.WorkoutSet;

import java.time.LocalDateTime;

public class WorkoutSetResource extends ResourceObject {
	private int repetition;
	private double weight;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timestamp;
	private ExerciseResource exercise;
	private UserResource user;

	public WorkoutSetResource() {
	}

	private WorkoutSetResource(WorkoutSet workoutSet) {
		this.repetition = workoutSet.getRepetition();
		this.weight = workoutSet.getWeight();
		this.timestamp = workoutSet.getTimestamp();
		this.exercise = ExerciseResource.from(workoutSet.getExercise());
		this.user = UserResource.from(workoutSet.getUser());
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

	public UserResource getUser() {
		return user;
	}

	public void setUser(UserResource user) {
		this.user = user;
	}
}
