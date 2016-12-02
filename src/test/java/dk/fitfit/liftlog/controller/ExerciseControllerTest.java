package dk.fitfit.liftlog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.resource.ExerciseResource;
import dk.fitfit.liftlog.resource.ResourceObject;
import dk.fitfit.liftlog.service.ExerciseServiceInterface;
import dk.fitfit.liftlog.service.MapperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExerciseController.class)
public class ExerciseControllerTest {

	@SuppressWarnings("SpringJavaAutowiredMembersInspection")
	@Autowired
	private MockMvc mvc;

	@MockBean
	private MapperService mapperService;

	@MockBean
	private ExerciseServiceInterface exerciseService;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void getExercises() throws Exception {
		Exercise exercise = new Exercise();
		List<Exercise> exercises = Lists.newArrayList(exercise);
		given(exerciseService.findAll())
				.willReturn(exercises);

		ExerciseResource exerciseResource = new ExerciseResource();
		exerciseResource.setName("name");
		exerciseResource.setDescription("description");
		Iterable<ResourceObject> exerciseResources = Lists.newArrayList(exerciseResource);

		given(mapperService.map(exercises))
				.willReturn(exerciseResources);

		mvc.perform(get("/exercises").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(mapper.writeValueAsString(exerciseResources)));
	}

	@Test
	public void getExercise() throws Exception {
		Exercise exercise = new Exercise();
		given(exerciseService.findOne(1))
				.willReturn(exercise);

		ExerciseResource exerciseResource = new ExerciseResource();
		exerciseResource.setName("name");
		exerciseResource.setDescription("description");

		given(mapperService.map(exercise))
				.willReturn(exerciseResource);

		mvc.perform(get("/exercises/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(mapper.writeValueAsString(exerciseResource)));
	}

}
