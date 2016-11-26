package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.FirstClassDomainObject;
import dk.fitfit.liftlog.resource.ResourceObject;

import java.util.List;

public interface ClassMapper<D extends FirstClassDomainObject, R extends ResourceObject> {
	R map(D domainObject);
	D map(R resourceObject);
	List<Class<?>> getSupportedClasses();
}
