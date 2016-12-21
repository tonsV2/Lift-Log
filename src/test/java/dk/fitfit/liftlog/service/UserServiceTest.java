package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class UserServiceTest {
	@Autowired
	private UserServiceInterface userService;

	private String sub = "sub";
	private String name = "name";
	private String email = "email";
	private User user;

	@Before
	public void setUp() {
		user = userService.create(sub, name, email);
	}

	@After
	public void tearDown() {
		userService.delete(user);
	}

	@Test
	public void findOne() {
		// Given

		// When
		User one = userService.findOne(user.getId());

		// Then
		assertThat(one.getSub()).isEqualTo(sub);
		assertThat(one.getUsername()).isEqualTo(name);
		assertThat(one.getEmail()).isEqualTo(email);
	}

	@Test
	public void save() {
		fail();
	}

	@Test
	public void delete() {
		// Given

		// When
		userService.delete(user);

		// Then
		User found = userService.findOne(user.getId());
		assertThat(found).isEqualTo(null);
	}

	@Test
	public void findBySub() {
		// Given

		// When
		User bySub = userService.findBySub(sub);

		// Then
		assertThat(bySub.getSub()).isEqualTo(sub);
		assertThat(bySub.getUsername()).isEqualTo(name);
		assertThat(bySub.getEmail()).isEqualTo(email);
	}

	@Test
	public void create() {
		// Given
		String sub = "create sub";
		String name = "create name";
		String email = "create email";

		// When
		User user = userService.create(sub, name, email);

		// Then
		assertThat(user.getSub()).isEqualTo(sub);
		assertThat(user.getUsername()).isEqualTo(name);
		assertThat(user.getEmail()).isEqualTo(email);

		// Clean up
		userService.delete(user);
	}

	@Test
	public void findByToken() {
		fail();
	}

}