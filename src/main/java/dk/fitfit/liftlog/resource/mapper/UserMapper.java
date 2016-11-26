package dk.fitfit.liftlog.resource.mapper;

import com.google.common.collect.Lists;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.resource.UserResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
interface UserMapper extends ClassMapper<User, UserResource> {
	@Override
	default List<Class<?>> getSupportedClasses() {
		return Lists.newArrayList(User.class, UserResource.class);
	}
}
