package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.resource.UserResource;
import dk.fitfit.liftlog.service.MapperService;
import dk.fitfit.liftlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	private final UserService userService;
	private final MapperService mapperService;

	@Autowired
	public UserController(UserService userService, MapperService mapperService) {
		this.userService = userService;
		this.mapperService = mapperService;
	}

	@GetMapping("/users")
	public Iterable<UserResource> users() {
		Iterable<User> users = userService.findAll();
		return mapperService.map(users);
	}

	@GetMapping("/users/{id}")
	public UserResource user(@PathVariable long id) {
		User user = userService.findOne(id);
		return mapperService.map(user);
	}
}
