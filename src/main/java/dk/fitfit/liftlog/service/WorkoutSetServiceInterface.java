package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;

import java.time.LocalDateTime;

public interface WorkoutSetServiceInterface {
	WorkoutSet log(User user, Exercise exercise, int repetition, double weight);
	WorkoutSet log(User user, Exercise exercise, int repetition, double weight, LocalDateTime localDateTime);
	WorkoutSet save(User user, WorkoutSet set);
	WorkoutSet findLastSet(User user);
}
