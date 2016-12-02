package dk.fitfit.liftlog.repository;

import dk.fitfit.liftlog.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
	User findBySub(String sub);
}
