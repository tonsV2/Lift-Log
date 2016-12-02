package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.repository.UserRepository;
import dk.fitfit.liftlog.security.GoogleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final GoogleAuth googleAuth;

	@Autowired
	public UserService(UserRepository userRepository, GoogleAuth googleAuth) {
		this.userRepository = userRepository;
		this.googleAuth = googleAuth;
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

	public User findByToken(String token) {
		String sub = googleAuth.getSub(token);
		String username = googleAuth.getUsername(token);
		String email = googleAuth.getEmail(token);
		User user = findBySub(sub);
		if(user == null) {
			user = create(sub, username, email);
		}
		return user;
	}
}
