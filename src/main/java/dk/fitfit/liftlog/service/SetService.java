package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.Set;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetService {
	private final SetRepository setRepository;

	@Autowired
	public SetService(SetRepository setRepository) {
		this.setRepository = setRepository;
	}

	public Iterable<Set> findAll() {
		return setRepository.findAll();
	}

	public Set log(User user, Exercise exercise, Set set) {
		set.setUser(user);
		set.setExercise(exercise);
		return setRepository.save(set);
	}

	public Set log(User user, Exercise exercise, int repetition, double weight) {
		Set set = new Set();
		set.setRepetition(repetition);
		set.setWight(weight);
		return log(user, exercise, set);
	}

	public Set save(Set set) {
		return setRepository.save(set);
	}
}
