package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.Session;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService implements SessionServiceInterface {
	private final SessionRepository sessionRepository;

	@Autowired
	public SessionService(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@Override
	public Session save(Session session) {
		return sessionRepository.save(session);
	}

	@Override
	public Iterable<Session> findAll(User user) {
		return null;
	}
}
