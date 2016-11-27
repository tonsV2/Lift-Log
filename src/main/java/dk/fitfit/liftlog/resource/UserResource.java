package dk.fitfit.liftlog.resource;

import dk.fitfit.liftlog.domain.User;

public class UserResource extends ResourceObject {
	private String name;
// TODO: Don't just expose email...
//	private String email;

	private UserResource(User user) {
		this.name = user.getName();
	}

	public static UserResource from(User user) {
		return new UserResource(user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
