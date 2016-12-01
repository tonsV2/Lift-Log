package dk.fitfit.liftlog.resource;

import dk.fitfit.liftlog.domain.User;

public class UserResource extends ResourceObject {
	private String username;
	private String email;

	public UserResource() {
	}

	private UserResource(User user) {
		this.username = user.getUsername();
	}

	public static UserResource from(User user) {
		return new UserResource(user);
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
}
