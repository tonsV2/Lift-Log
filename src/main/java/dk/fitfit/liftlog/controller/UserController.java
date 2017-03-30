package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.resource.UserResource;
import dk.fitfit.liftlog.security.CurrentUserHolder;
import dk.fitfit.liftlog.service.MapperService;
import dk.fitfit.liftlog.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
	private final CurrentUserHolder currentUserHolder;
	private final UserServiceInterface userService;
	private final MapperService mapperService;

	@Autowired
	public UserController(CurrentUserHolder currentUserHolder, UserServiceInterface userService, MapperService mapperService) {
		this.currentUserHolder = currentUserHolder;
		this.userService = userService;
		this.mapperService = mapperService;
	}

	@RequestMapping("/users/principal")
	public Principal user(Principal principal) {
		return principal;
	}

	//@GetMapping("/users")
	@RequestMapping("/users")
	public UserResource getUser() {
		User user = currentUserHolder.getUser();
		return UserResource.from(user);
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("@currentUserHolder.getUser().id.equals(#id)")
	public UserResource user(@PathVariable long id) {
		User user = userService.findOne(id);
		return mapperService.map(user);
	}
}
