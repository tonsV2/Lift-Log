package dk.fitfit.liftlog.resource.mapper;

import com.google.common.collect.Lists;
import dk.fitfit.liftlog.domain.Session;
import dk.fitfit.liftlog.resource.SessionResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {WorkoutSetMapper.class})
interface SessionMapper extends ClassMapper<Session, SessionResource> {
	@Override
	default List<Class<?>> getSupportedClasses() {
		return Lists.newArrayList(Session.class, SessionResource.class);
	}
}
