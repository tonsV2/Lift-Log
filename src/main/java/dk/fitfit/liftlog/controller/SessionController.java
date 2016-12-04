package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Session;
import dk.fitfit.liftlog.resource.SessionResource;
import dk.fitfit.liftlog.security.CurrentUserHolder;
import dk.fitfit.liftlog.service.MapperService;
import dk.fitfit.liftlog.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
	private final CurrentUserHolder currentUserHolder;
	private final MapperService mapperService;
	private final SessionService sessionService;

	@Autowired
	public SessionController(CurrentUserHolder currentUserHolder, MapperService mapperService, SessionService sessionService) {
		this.currentUserHolder = currentUserHolder;
		this.mapperService = mapperService;
		this.sessionService = sessionService;
	}

	@GetMapping("/sessions")
	public Iterable<SessionResource> getSessions() {
		Iterable<Session> sessions = sessionService.findAll(currentUserHolder.getUser());
		return mapperService.map(sessions);
	}
}
