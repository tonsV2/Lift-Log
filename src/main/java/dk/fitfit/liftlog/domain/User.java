package dk.fitfit.liftlog.domain;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class User extends org.springframework.security.core.userdetails.User implements DomainObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	@OneToMany
	private Set<WorkoutSet> workoutSets;

	public User() {
		super("dummy", "dummy", Lists.newArrayList());
	}

	public User(String username, String email) {
		super(username, "", Lists.newArrayList());
		this.email = email;
	}

	public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
