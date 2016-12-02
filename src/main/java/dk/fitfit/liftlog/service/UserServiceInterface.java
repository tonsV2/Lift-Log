package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.User;

public interface UserServiceInterface {
	User findOne(long id);
	User save(User user);
	void delete(User user);
	User findBySub(String sub);
	User create(String sub, String name, String email);
	User findByToken(String token);
}
