package dk.fitfit.liftlog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	@OneToMany
	private List<Set> sets;

	public Exercise() {
	}

	public Exercise(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
