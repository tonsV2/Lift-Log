package dk.fitfit.liftlog.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User implements DomainObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	@OneToMany
	private Set<WorkoutSet> workoutSets;

	public User() {
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<WorkoutSet> getWorkoutSets() {
		return workoutSets;
	}

	public void setWorkoutSets(Set<WorkoutSet> workoutSets) {
		this.workoutSets = workoutSets;
	}
}
