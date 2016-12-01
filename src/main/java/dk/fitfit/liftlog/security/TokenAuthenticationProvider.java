package dk.fitfit.liftlog.security;


import dk.fitfit.liftlog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
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
//		User user = userSvc.validateApiAuthenticationToken(token);
		User user = userService.findByEmail(token); // Validate token and find user
		if (user != null) {
			auth = new PreAuthenticatedAuthenticationToken(user, token);
			auth.setAuthenticated(true);
		} else {
			throw new BadCredentialsException("Invalid token " + token);
		}
		return auth;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}

}
