package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.FirstClassDomainObject;
import dk.fitfit.liftlog.resource.mapper.ToResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapperService {
	private Map<Class<?>, ToResource<?, ?>> mappers = new HashMap<>();

	@Autowired
	public MapperService(List<ToResource<? extends FirstClassDomainObject, ? extends ResourceSupport>> mappers) {
		for (ToResource<? extends FirstClassDomainObject, ? extends ResourceSupport> mapper : mappers) {
			for (Class<?> supportedClass : mapper.getSupportedClasses()) {
				this.mappers.put(supportedClass, mapper);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <D extends FirstClassDomainObject, R extends ResourceSupport> ToResource<D, R> getMapper(Class<?> domainObjectClass) {
		return (ToResource<D, R>) mappers.get(domainObjectClass);
	}

	public <D extends FirstClassDomainObject, R extends ResourceSupport> R map(D domainObject) {
		ToResource<D, R> mapper = getMapper(domainObject.getClass());
		return mapper.map(domainObject);
	}

	public <D extends FirstClassDomainObject, R extends ResourceSupport> D map(R resourceObject) {
		ToResource<D, R> mapper = getMapper(resourceObject.getClass());
		return mapper.map(resourceObject);
	}

// TODO: How to reverse this method... Swapping D and R on the Iterable's give the same method erasure (generic types are removed during compilation)
//	public <D extends FirstClassDomainObject, R extends ResourceSupport> Iterable<D> map(Iterable<R> domainObjects) {
	public <D extends FirstClassDomainObject, R extends ResourceSupport> Iterable<R> map(Iterable<D> domainObjects) {
		List<R> resources = new ArrayList<>();
		for (D domainObject : domainObjects) {
			resources.add(map(domainObject));
		}
		return resources;
	}
}
