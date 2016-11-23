package dk.fitfit.liftlog.repository;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}
