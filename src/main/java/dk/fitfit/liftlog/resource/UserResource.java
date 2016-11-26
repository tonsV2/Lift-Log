package dk.fitfit.liftlog.resource;

public class UserResource implements ResourceObject {
	private Long id;
	private String name;
// TODO: Don't just expose email...
//	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
