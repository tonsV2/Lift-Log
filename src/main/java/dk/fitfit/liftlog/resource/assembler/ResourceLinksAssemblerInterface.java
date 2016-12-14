package dk.fitfit.liftlog.resource.assembler;

import dk.fitfit.liftlog.domain.DomainObject;
import org.springframework.hateoas.Link;

import java.util.List;

public interface ResourceLinksAssemblerInterface<T extends DomainObject> {
	List<Link> getLinks(T entity);
}
