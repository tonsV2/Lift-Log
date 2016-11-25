package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.FirstClassDomainObject;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

// Rename to Mapper... or probably Assembler so it wont clash with mapstruct @Mapper
interface ToResource<D extends FirstClassDomainObject, R extends ResourceSupport> {
	R toResource(D domainObject);
	List<R> toResources(List<D> domainObjects);
	Class<D> getSupportedClass();
}
