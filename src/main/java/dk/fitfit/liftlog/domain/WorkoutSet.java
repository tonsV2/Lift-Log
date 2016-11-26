package dk.fitfit.liftlog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class WorkoutSet implements FirstClassDomainObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int repetition;
	private double wight;
	private LocalDateTime timestamp;
	@ManyToOne
	private Exercise exercise;
	@ManyToOne
	private User user;

	public WorkoutSet() {
		this.timestamp = LocalDateTime.now();
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
