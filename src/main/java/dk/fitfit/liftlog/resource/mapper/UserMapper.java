package dk.fitfit.liftlog.resource.mapper;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.resource.UserResource;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
abstract class UserMapper implements ToResource<User, UserResource> {
	@Override
	public abstract UserResource toResource(User domainObject);
	public abstract List<UserResource> toResources(List<User> domainObject);

	@Override
	public Class<User> getSupportedClass() {
		return User.class;
	}
}
