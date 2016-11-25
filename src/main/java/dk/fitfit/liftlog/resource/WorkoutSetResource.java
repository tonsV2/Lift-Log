package dk.fitfit.liftlog.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class WorkoutSetResource {
	private int repetition;
	private double wight;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timestamp;
	private ExerciseResource exercise;
	private UserResource user;

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public double getWight() {
		return wight;
	}

	public void setWight(double wight) {
		this.wight = wight;
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
