package dk.fitfit.liftlog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	@OneToMany
	private List<Set> sets;

	public User() {
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}
}
