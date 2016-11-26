package dk.fitfit.liftlog.resource.mapper;

import com.google.common.collect.Lists;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.resource.WorkoutSetResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ExerciseMapper.class})
interface WorkoutSetMapper extends ClassMapper<WorkoutSet, WorkoutSetResource> {
	@Override
	default List<Class<?>> getSupportedClasses() {
		return Lists.newArrayList(WorkoutSet.class, WorkoutSetResource.class);
	}
}
