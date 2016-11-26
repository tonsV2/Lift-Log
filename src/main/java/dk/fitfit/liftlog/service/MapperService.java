package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.FirstClassDomainObject;
import dk.fitfit.liftlog.resource.ResourceObject;
import dk.fitfit.liftlog.resource.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapperService {
	private Map<Class<?>, ClassMapper<?, ?>> mappers = new HashMap<>();

	@Autowired
	public MapperService(List<ClassMapper<? extends FirstClassDomainObject, ? extends ResourceObject>> mappers) {
		for (ClassMapper<? extends FirstClassDomainObject, ? extends ResourceObject> mapper : mappers) {
			for (Class<?> supportedClass : mapper.getSupportedClasses()) {
				this.mappers.put(supportedClass, mapper);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <D extends FirstClassDomainObject, R extends ResourceObject> ClassMapper<D, R> getMapper(Class<?> domainObjectClass) {
		return (ClassMapper<D, R>) mappers.get(domainObjectClass);
	}

	public <D extends FirstClassDomainObject, R extends ResourceObject> R map(D domainObject) {
		ClassMapper<D, R> mapper = getMapper(domainObject.getClass());
		return mapper.map(domainObject);
	}

	public <D extends FirstClassDomainObject, R extends ResourceObject> D map(R resourceObject) {
		ClassMapper<D, R> mapper = getMapper(resourceObject.getClass());
		return mapper.map(resourceObject);
	}

// TODO: How to reverse this method... Swapping D and R on the Iterable's give the same method erasure (generic types are removed during compilation)
//	public <D extends FirstClassDomainObject, R extends ResourceObject> Iterable<D> map(Iterable<R> domainObjects) {
	public <D extends FirstClassDomainObject, R extends ResourceObject> Iterable<R> map(Iterable<D> domainObjects) {
		List<R> resources = new ArrayList<>();
		for (D domainObject : domainObjects) {
			resources.add(map(domainObject));
		}
		return resources;
	}
}
