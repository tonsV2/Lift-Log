package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.FirstClassDomainObject;
import org.mapstruct.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

// Rename to Mapper... or probably Assembler so it wont clash with mapstruct @Mapper
interface ToResource<D extends FirstClassDomainObject, R extends ResourceSupport> {
	R map(D domainObject);
	@Mapping(target = "id", ignore = true)
	D map(R resourceObject);
//	Iterable<R> map(Iterable<D> domainObjects);
//	Iterable<D> map(Iterable<R> resourceObjects);
	List<Class<?>> getSupportedClasses();
}
