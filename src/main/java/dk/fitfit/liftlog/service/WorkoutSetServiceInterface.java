package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;

import java.time.LocalDateTime;

public interface WorkoutSetServiceInterface {
	Iterable<WorkoutSet> findAll(User user);
	WorkoutSet log(User user, Exercise exercise, WorkoutSet set);
	WorkoutSet log(User user, Exercise exercise, int repetition, double weight);
	WorkoutSet log(User user, Exercise exercise, int repetition, double weight, LocalDateTime localDateTime);
	WorkoutSet save(WorkoutSet set);
}
