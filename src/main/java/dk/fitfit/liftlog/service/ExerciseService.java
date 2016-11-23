package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.Set;
import dk.fitfit.liftlog.repository.ExerciseRepository;
import dk.fitfit.liftlog.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
	private final ExerciseRepository exerciseRepository;

	@Autowired
	public ExerciseService(ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}

	public Iterable<Exercise> findAll() {
		return exerciseRepository.findAll();
	}

	public Exercise save(Exercise exercise) {
		return exerciseRepository.save(exercise);
	}
}
