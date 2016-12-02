package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.Exercise;

public interface ExerciseServiceInterface {
	Iterable<Exercise> findAll();
	Exercise findOne(long id);
	Exercise save(Exercise exercise);
}
