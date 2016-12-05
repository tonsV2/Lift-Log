package dk.fitfit.liftlog.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context.getAuthentication() == null || !context.getAuthentication().isAuthenticated()) {
			String token = getToken(request);
			if (token != null) {
				Authentication auth = new TokenAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.startsWith("Basic ")) {
			String base64Credentials = authorization.substring("Basic ".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
			String[] values = credentials.split(":", 2);
			return values[1];
		}
		return null;
	}
}
