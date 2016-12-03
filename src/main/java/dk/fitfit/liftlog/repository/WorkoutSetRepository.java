package dk.fitfit.liftlog.repository;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutSetRepository extends CrudRepository<WorkoutSet, Long> {
	Iterable<WorkoutSet> findByUser(User user);
	List<WorkoutSet> findByUserOrderByTimestampDesc(User user, Pageable limit);
}
