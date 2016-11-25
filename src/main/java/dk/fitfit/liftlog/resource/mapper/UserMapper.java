package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.resource.UserResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
abstract class UserMapper implements ToResource<User, UserResource> {
	@Override
	public Class<User> getSupportedClass() {
		return User.class;
	}
}
