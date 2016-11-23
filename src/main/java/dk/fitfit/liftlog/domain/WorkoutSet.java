package dk.fitfit.liftlog.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class WorkoutSet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int repetition;
	private double wight;
	// TODO: Should be on the DTO
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timestamp;
	@ManyToOne
	private Exercise exercise;
	@ManyToOne
	private User user;

	public WorkoutSet() {
		this.timestamp = LocalDateTime.now();
	}
}
