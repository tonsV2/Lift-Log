package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.FirstClassDomainObject;
import org.springframework.hateoas.ResourceSupport;

// Rename to Mapper... or probably Assembler so it wont clash with mapstruct @Mapper
interface ToResource<D extends FirstClassDomainObject, R extends ResourceSupport> {
	R map(D domainObject);
	Iterable<R> map(Iterable<D> domainObjects);
	Class<D> getSupportedClass();
}
