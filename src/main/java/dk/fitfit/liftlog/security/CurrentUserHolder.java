package dk.fitfit.liftlog.security;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CurrentUserHolder {
	private final UserService userService;

	@Autowired
	public CurrentUserHolder(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		String sub = getSub();
		User user = userService.findBySub(sub);
		if (user == null) {
			String name = getName();
			String email = getEmail();
			user = userService.create(sub, name, email);
		}
		return user;
	}

	private String getSub() {
		return (String) getUserDetails().get("sub");
	}

	private String getName() {
		return (String) getUserDetails().get("name");
	}

	private String getEmail() {
		return (String) getUserDetails().get("email");
	}

	private Map getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
		Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
		return (Map) userAuthentication.getDetails();
	}
}
