package dk.fitfit.liftlog.controller;

import dk.fitfit.liftlog.resource.ApiResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public ApiResource getApi() {
		return new ApiResource();
	}
}
