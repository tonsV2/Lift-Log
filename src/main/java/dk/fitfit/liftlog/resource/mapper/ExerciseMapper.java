package dk.fitfit.liftlog.resource.mapper;

import com.google.common.collect.Lists;
import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.resource.ExerciseResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
interface ExerciseMapper extends ClassMapper<Exercise, ExerciseResource> {
	@Override
	default List<Class<?>> getSupportedClasses() {
		return Lists.newArrayList(Exercise.class, ExerciseResource.class);
	}
}
