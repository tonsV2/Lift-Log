package dk.fitfit.liftlog.resource;

import com.google.common.collect.Streams;
import dk.fitfit.liftlog.domain.User;

import java.util.stream.Collectors;

public class UserResource extends ResourceObject {
	private String username;
	private String email;
	private String sub;

	public UserResource() {
	}

	private UserResource(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.sub = user.getSub();
	}

	public static UserResource from(User user) {
		return new UserResource(user);
	}

	public static Iterable<UserResource> from(Iterable<User> users) {
        return Streams.stream(users)
				.map(UserResource::from)
				.collect(Collectors.toList());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}
}
