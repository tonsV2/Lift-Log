package dk.fitfit.liftlog.config;

import dk.fitfit.liftlog.security.AuthenticationTokenFilter;
import dk.fitfit.liftlog.security.TokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final AuthenticationTokenFilter tokenFilter;
	private final TokenAuthenticationProvider tokenAuthenticationProvider;

	@Autowired
	public SecurityConfiguration(AuthenticationTokenFilter tokenFilter, TokenAuthenticationProvider tokenAuthenticationProvider) {
		this.tokenFilter = tokenFilter;
		this.tokenAuthenticationProvider = tokenAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class)
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/", "/login**").permitAll()
				.anyRequest().authenticated()
				.and()
				.logout().logoutSuccessUrl("/").permitAll()
				.and()
				.csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"));
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(tokenAuthenticationProvider);
	}

	// Inspiration: https://spring.io/guides/gs/rest-service-cors/
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
