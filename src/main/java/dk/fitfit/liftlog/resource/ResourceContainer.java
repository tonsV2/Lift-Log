package dk.fitfit.liftlog.resource;

import java.util.List;

public class ResourceContainer<T extends ResourceObject> extends ResourceObject {
	private final List<T> content;

	public ResourceContainer(List<T> content) {
		this.content = content;
	}

	public List<T> getContent() {
		return content;
	}
}
