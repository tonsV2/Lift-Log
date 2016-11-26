package dk.fitfit.liftlog.resource.mapper;

import com.google.common.collect.Lists;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.resource.UserResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
interface UserMapper extends ClassMapper<User, UserResource> {
	@Override
	UserResource map(User user);

	@Override
	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(target = "workoutSets", ignore = true)
	})
	User map(UserResource resource);

	@Override
	default List<Class<?>> getSupportedClasses() {
		return Lists.newArrayList(User.class, UserResource.class);
	}
}
