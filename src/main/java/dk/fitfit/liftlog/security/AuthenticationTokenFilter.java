package dk.fitfit.liftlog.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

@Component
public class AuthenticationTokenFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
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

	private String getToken(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authorization = httpRequest.getHeader("Authorization");
		if (authorization != null && authorization.startsWith("Basic ")) {
			String base64Credentials = authorization.substring("Basic ".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
			String[] values = credentials.split(":", 2);
			return values[1];
		}
		return null;
	}

	@Override
	public void destroy() {
	}
}
