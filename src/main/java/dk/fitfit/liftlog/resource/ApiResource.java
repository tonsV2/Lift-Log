package dk.fitfit.liftlog.resource;

import dk.fitfit.liftlog.controller.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ApiResource extends ResourceObject {
	private String title = "Lift Logger API";
	private String description = "Rest API for Lift Logger";
	private String version = "0.1";

	public ApiResource() {
		// TODO: Get these links from a links assembler and assemble this resource in the controller like any other resource... except that there is no input
		add(linkTo(methodOn(ApiController.class).getApi()).withSelfRel());
		add(linkTo(methodOn(UserController.class).getUser()).withRel("users"));
		add(linkTo(methodOn(ExerciseController.class).getExercises()).withRel("exercises"));
		add(linkTo(methodOn(SessionController.class).getSessions()).withRel("sessions"));
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getVersion() {
		return version;
	}
}
