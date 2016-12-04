package dk.fitfit.liftlog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Session implements DomainObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDateTime timestamp = LocalDateTime.now();
	@ManyToOne
	private User user;
	@OneToMany(mappedBy = "session", fetch = FetchType.EAGER)
	private Set<WorkoutSet> sets;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<WorkoutSet> getSets() {
		return sets;
	}

	public void setSets(Set<WorkoutSet> sets) {
		this.sets = sets;
	}
}
