package dk.fitfit.liftlog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class Set {
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

	public Set() {
		this.timestamp = LocalDateTime.now();
	}
}
