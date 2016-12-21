package dk.fitfit.liftlog.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Exercise implements DomainObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String name;
	@Column(length = 1020)
	private String description;
	@OneToMany(mappedBy = "exercise")
	private Set<WorkoutSet> workoutSets;

	public Exercise() {
		// Used by hibernate
	}

	public Exercise(String name, String description) {
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<WorkoutSet> getWorkoutSets() {
		return workoutSets;
	}

	public void setWorkoutSets(Set<WorkoutSet> workoutSets) {
		this.workoutSets = workoutSets;
	}
}
