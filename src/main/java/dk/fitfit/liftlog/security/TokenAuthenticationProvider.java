package dk.fitfit.liftlog.security;

import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
	private final UserService userService;

	@Autowired
	public TokenAuthenticationProvider(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		if (auth.isAuthenticated()) {
			return auth;
		}
		String token = auth.getCredentials().toString();
		User user = validateTokenAndFindUser(token);
		if (user != null) {
			auth = new PreAuthenticatedAuthenticationToken(user, token);
			auth.setAuthenticated(true);
		} else {
			throw new BadCredentialsException("Invalid token " + token);
		}
		return auth;
	}

	private User validateTokenAndFindUser(String token) {
		return userService.findByEmail(token);
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return getClass().isAssignableFrom(aClass);
	}
}
