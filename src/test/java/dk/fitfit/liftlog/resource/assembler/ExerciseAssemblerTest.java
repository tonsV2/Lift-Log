package dk.fitfit.liftlog.resource.assembler;

import com.google.common.collect.Lists;
import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.resource.ExerciseResource;
import dk.fitfit.liftlog.resource.ResourceContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class ExerciseAssemblerTest {
	@Autowired
	private ExerciseAssembler assembler;

	private String name = "name";
	private String description = "description";

	@Test
	public void toResource() throws Exception {
		// Given
		Exercise exercise = new Exercise(name, description);

		// When
		ExerciseResource resource = assembler.toResource(exercise);

		// Then
		assertThat(resource.getName()).isEqualTo(name);
		assertThat(resource.getDescription()).isEqualTo(description);
	}

	@Test
	public void toResources() throws Exception {
		// Given
		Exercise exercise1 = new Exercise(name, description);
		exercise1.setId(1);
		Exercise exercise2 = new Exercise(name + "2", description + "2");
		exercise2.setId(2);

		List<Exercise> exercises = Lists.newArrayList(exercise1, exercise2);

		// When
		ResourceContainer<ExerciseResource> resourceContainer = assembler.toResources(exercises);

		// Then
		// TODO: Somethings wrong here... Even though I implement equals and hashCode on ExerciseResource this still doesn't work
		exercise1.setName("sdsds");
		exercise1.setDescription("SDSDSDSDSDS");
		List<ExerciseResource> content = resourceContainer.getContent();
		assertThat(content.size()).isEqualTo(2);

		ExerciseResource resource1 = assembler.toResource(exercise1);
		assertThat(content).contains(resource1);
		assertThat(content).contains(assembler.toResource(exercise2));
	}

}
