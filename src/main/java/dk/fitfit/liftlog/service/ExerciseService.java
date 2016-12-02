package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService implements ExerciseServiceInterface {
	private final ExerciseRepository exerciseRepository;

	@Autowired
	public ExerciseService(ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}

	@Override
	public Iterable<Exercise> findAll() {
		return exerciseRepository.findAll();
	}

	@Override
	public Exercise findOne(long id) {
		return exerciseRepository.findOne(id);
	}

	@Override
	public Exercise save(Exercise exercise) {
		return exerciseRepository.save(exercise);
	}
}
