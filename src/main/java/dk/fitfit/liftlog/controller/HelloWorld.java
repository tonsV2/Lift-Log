package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.Set;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.service.ExerciseService;
import dk.fitfit.liftlog.service.SetService;
import dk.fitfit.liftlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	private final SetService setService;
	private final ExerciseService exerciseService;
	private final UserService userService;

	@Autowired
	public HelloWorld(SetService setService, ExerciseService exerciseService, UserService userService) {
		this.setService = setService;
		this.exerciseService = exerciseService;
		this.userService = userService;
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello world!";
	}

	@GetMapping("/sets")
	public Iterable<Set> sets() {
		return setService.findAll();
	}

	@GetMapping("/init")
	public Iterable<Set> init() {
		User user = new User("tons", "tons@tons.dk");
		userService.save(user);

		Exercise bp = new Exercise("Bench press", "Pecks, baby!");
		exerciseService.save(bp);
		Exercise squat = new Exercise("Squat", "Never skip leg day!");
		exerciseService.save(squat);

		Set set = new Set();
		set.setRepetition(10);
		set.setWight(70D);

		setService.log(user, bp, set);
		setService.log(user, squat, 8, 70D);

		return setService.findAll();
	}

}
