package dk.fitfit.liftlog.resource;

import com.google.common.collect.Streams;
import dk.fitfit.liftlog.domain.Session;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class SessionResource extends ResourceObject {
	private LocalDateTime timestamp = LocalDateTime.now();
	private Iterable<WorkoutSetResource> sets;

	public SessionResource() {
	}

	private SessionResource(Session session) {
		this.timestamp = session.getTimestamp();
		this.sets = WorkoutSetResource.from(session.getSets());
	}

	public static SessionResource from(Session session) {
		return new SessionResource(session);
	}

	public static Iterable<SessionResource> from(Iterable<Session> sessions) {
		return Streams.stream(sessions)
				.map(SessionResource::from)
				.collect(Collectors.toList());
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Iterable<WorkoutSetResource> getSets() {
		return sets;
	}

	public void setSets(Iterable<WorkoutSetResource> sets) {
		this.sets = sets;
	}
}
