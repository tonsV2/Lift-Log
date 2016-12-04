package dk.fitfit.liftlog.repository;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutSetRepository extends CrudRepository<WorkoutSet, Long> {
	// TODO: How does this work without overriding equals on user so it only compares by id? Because I only have one user in the db?
	@Query("select w from WorkoutSet w inner join w.session s where s.user = :user order by w.timestamp desc")
	List<WorkoutSet> findLastSet(@Param("user") User user, Pageable limit);
}
