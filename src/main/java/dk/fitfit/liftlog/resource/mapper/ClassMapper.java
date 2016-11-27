package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.DomainObject;
import dk.fitfit.liftlog.resource.ResourceObject;
import org.mapstruct.Mapping;

import java.util.List;

public interface ClassMapper<D extends DomainObject, R extends ResourceObject> {
	R map(D domainObject);
	@Mapping(target = "id", ignore = true)
	D map(R resourceObject);
	List<Class<?>> getSupportedClasses();
}
