package dk.fitfit.liftlog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.resource.ResourceObject;
import dk.fitfit.liftlog.resource.UserResource;
import dk.fitfit.liftlog.service.MapperService;
import dk.fitfit.liftlog.service.UserServiceInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class UserControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private MapperService mapperService;

	private ObjectMapper mapper = new ObjectMapper();
	private User user;
	private UserResource userResource;

	@Before
	public void setUp() throws Exception {
		user = new User("some random sub", "username", "user@email.com");
		userService.save(user);
		userResource = mapperService.map(user);
	}

	@After
	public void tearDown() {
		userService.delete(user);
	}

	@Test
	public void users() throws Exception {
		List<ResourceObject> userResources = Lists.newArrayList(userResource);
		mvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(mapper.writeValueAsString(userResources)));
	}

	@Test
	public void user() throws Exception {
		mvc.perform(get("/users/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(mapper.writeValueAsString(userResource)));
	}
}
