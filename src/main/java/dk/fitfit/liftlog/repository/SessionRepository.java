package dk.fitfit.liftlog.repository;

import dk.fitfit.liftlog.domain.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
}
