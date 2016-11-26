package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.resource.UserResource;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
abstract class UserMapper implements ToResource<User, UserResource> {
	@Override
	public List<Class<?>> getSupportedClasses() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(User.class);
		classes.add(UserResource.class);
		return classes;
	}
}
