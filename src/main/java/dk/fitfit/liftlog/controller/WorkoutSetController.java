package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.resource.WorkoutSetResource;
import dk.fitfit.liftlog.service.MapperService;
import dk.fitfit.liftlog.service.UserService;
import dk.fitfit.liftlog.service.WorkoutSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkoutSetController {
	private final WorkoutSetService workoutSetService;
	private final MapperService mapperService;
	private final UserService userService;

	@Autowired
	public WorkoutSetController(UserService userService, WorkoutSetService workoutSetService, MapperService mapperService) {
		this.userService = userService;
		this.workoutSetService = workoutSetService;
		this.mapperService = mapperService;
	}

	@PostMapping("/sets")
	public WorkoutSetResource postSets(@RequestBody WorkoutSetResource workoutSetResource) {
		return mapperService.map(workoutSetResource);
	}

	@GetMapping("/sets")
	public Iterable<WorkoutSet> sets() {
		return workoutSetService.findAll();
	}
}
