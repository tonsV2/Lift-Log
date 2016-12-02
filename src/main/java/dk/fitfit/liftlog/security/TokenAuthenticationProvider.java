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
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication.isAuthenticated()) {
			return authentication;
		}
		String token = authentication.getCredentials().toString();
		User user = findUserByToken(token);
		if (user != null) {
			authentication = new PreAuthenticatedAuthenticationToken(user, token);
			authentication.setAuthenticated(true);
		} else {
			throw new BadCredentialsException("Invalid token " + token);
		}
		return authentication;
	}

	private User findUserByToken(String token) {
		return userService.findByToken(token);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return TokenAuthentication.class.isAssignableFrom(authentication);
	}
}
