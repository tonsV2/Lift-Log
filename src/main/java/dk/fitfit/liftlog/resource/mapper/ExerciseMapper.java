package dk.fitfit.liftlog.resource.mapper;

import com.google.common.collect.Lists;
import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.resource.ExerciseResource;
import dk.fitfit.liftlog.resource.UserResource;
import dk.fitfit.liftlog.resource.WorkoutSetResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
interface ExerciseMapper extends ClassMapper<Exercise, ExerciseResource> {
	@Override
//	@Mapping(target = "workoutSets", ignore = true)
	Exercise map(ExerciseResource resource);

	@Override
	default List<Class<?>> getSupportedClasses() {
		return Lists.newArrayList(Exercise.class, ExerciseResource.class);
	}
}
