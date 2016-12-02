package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findOne(long id) {
		return userRepository.findOne(id);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public User findBySub(String sub) {
		return userRepository.findBySub(sub);
	}

	public User create(String sub, String name, String email) {
		User user = new User(sub, name, email);
		return userRepository.save(user);
	}
}
