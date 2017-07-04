package dk.fitfit.liftlog.resource;

import org.springframework.hateoas.Link;

import java.util.List;

public class ResourceContainer<T extends ResourceObject> extends ResourceObject {
	private final List<T> content;

	public ResourceContainer(List<T> content, List<Link> containerLinks) {
		this.content = content;
		add(containerLinks);
	}

	public List<T> getContent() {
		return content;
	}
}
