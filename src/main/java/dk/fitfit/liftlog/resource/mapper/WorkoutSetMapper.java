package dk.fitfit.liftlog.resource.mapper;

import com.google.common.collect.Lists;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.resource.UserResource;
import dk.fitfit.liftlog.resource.WorkoutSetResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, WorkoutSetMapper.class})
interface WorkoutSetMapper extends ClassMapper<WorkoutSet, WorkoutSetResource> {
	@Override
	@Mappings({
			@Mapping(target = "user", ignore = true),
			@Mapping(target = "exercise", ignore = true)
	})
	WorkoutSet map(WorkoutSetResource resource);

	@Override
	@Mappings({
			@Mapping(target = "user", ignore = true),
			@Mapping(target = "exercise", ignore = true)
	})
	WorkoutSetResource map(WorkoutSet resource);

	@Override
	default List<Class<?>> getSupportedClasses() {
		return Lists.newArrayList(WorkoutSet.class, WorkoutSetResource.class);
	}
}
