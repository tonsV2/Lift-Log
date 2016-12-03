package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.Session;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.repository.WorkoutSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkoutSetService implements WorkoutSetServiceInterface {
	private final WorkoutSetRepository workoutSetRepository;
	private final SessionServiceInterface sessionService;

	@Autowired
	public WorkoutSetService(WorkoutSetRepository workoutSetRepository, SessionServiceInterface sessionService) {
		this.workoutSetRepository = workoutSetRepository;
		this.sessionService = sessionService;
	}

	@Override
	public Iterable<WorkoutSet> findAll(User user) {
		return workoutSetRepository.findByUser(user);
	}

	@Override
	public WorkoutSet log(User user, Exercise exercise, int repetition, double weight) {
		WorkoutSet set = new WorkoutSet();
		set.setUser(user);
		set.setExercise(exercise);
		set.setRepetition(repetition);
		set.setWeight(weight);
		return save(user, set);
	}

	@Override
	public WorkoutSet log(User user, Exercise exercise, int repetition, double weight, LocalDateTime localDateTime) {
		WorkoutSet set = new WorkoutSet();
		set.setExercise(exercise);
		set.setRepetition(repetition);
		set.setWeight(weight);
		set.setTimestamp(localDateTime);
		return save(user, set);
	}

	@Override
	public WorkoutSet save(User user, WorkoutSet set) {
		set.setUser(user);
		WorkoutSet lastSet = findLastSet(user);
		if(lastSet != null && sameSession(lastSet, set)) {
			Session session = lastSet.getSession();
			set.setSession(session);
		} else {
			Session session = new Session();
			session.setUser(user);
			sessionService.save(session);
			set.setSession(session);
		}
		return workoutSetRepository.save(set);
	}

	private boolean sameSession(WorkoutSet lastSet, WorkoutSet set) {
		return Duration.between(lastSet.getTimestamp(), set.getTimestamp()).toMinutes() < 10;
	}

	@Override
	public WorkoutSet findLastSet(User user) {
		Pageable limit = new PageRequest(0, 1);
		List<WorkoutSet> sets = workoutSetRepository.findByUserOrderByTimestampDesc(user, limit);
		if(sets.isEmpty()) {
			return null;
		}
		return sets.get(0);
	}
}
