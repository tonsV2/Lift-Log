package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.FirstClassDomainObject;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

@Component
public interface ToResource<D extends FirstClassDomainObject, R extends ResourceSupport> {
	R toResource(D domainObject);
	Class<D> getSupportedClass();
}
