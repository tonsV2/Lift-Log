package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.FirstClassDomainObject;
import org.mapstruct.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public interface ClassMapper<D extends FirstClassDomainObject, R extends ResourceSupport> {
	R map(D domainObject);
	@Mapping(target = "id", ignore = true) // Ignore self link on incoming resources
	D map(R resourceObject);
	List<Class<?>> getSupportedClasses();
}
