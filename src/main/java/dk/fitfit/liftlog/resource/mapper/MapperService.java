package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.FirstClassDomainObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapperService {
	private Map<Class<? extends FirstClassDomainObject>, ToResource<?, ?>> mappers = new HashMap<>();

	@Autowired
	public MapperService(List<ToResource<? extends FirstClassDomainObject, ? extends ResourceSupport>> mappers) {
		for (ToResource<? extends FirstClassDomainObject, ? extends ResourceSupport> mapper : mappers) {
			this.mappers.put(mapper.getSupportedClass(), mapper);
		}
	}

	@SuppressWarnings("unchecked")
	public <D extends FirstClassDomainObject, R extends ResourceSupport> ToResource<D, R> getMapper(Class<D> domainObjectClass) {
		return (ToResource<D, R>) mappers.get(domainObjectClass);
	}
}
