package dk.fitfit.liftlog.resource;

import dk.fitfit.liftlog.domain.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessionResource extends ResourceObject {
	private LocalDateTime timestamp = LocalDateTime.now();
	private List<WorkoutSetResource> sets;

	public SessionResource() {
	}

	private SessionResource(Session session) {
		this.timestamp = session.getTimestamp();
		this.sets = WorkoutSetResource.from(session.getSets());
	}

	public static SessionResource from(Session session) {
		return new SessionResource(session);
	}

	public static List<SessionResource> from(Iterable<Session> sessions) {
		List<SessionResource> resources = new ArrayList<>();
		for (Session session : sessions) {
			SessionResource resource = SessionResource.from(session);
			resources.add(resource);
		}
		return resources;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public List<WorkoutSetResource> getSets() {
		return sets;
	}

	public void setSets(List<WorkoutSetResource> sets) {
		this.sets = sets;
	}
}
