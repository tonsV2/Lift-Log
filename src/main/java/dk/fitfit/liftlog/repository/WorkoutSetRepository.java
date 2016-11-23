package dk.fitfit.liftlog.repository;

import dk.fitfit.liftlog.domain.WorkoutSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutSetRepository extends CrudRepository<WorkoutSet, Long> {
}
