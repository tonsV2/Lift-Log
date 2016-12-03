package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.Session;
import dk.fitfit.liftlog.domain.User;

public interface SessionServiceInterface {
	Session save(Session session);
	Iterable<Session> findAll(User user);
}
