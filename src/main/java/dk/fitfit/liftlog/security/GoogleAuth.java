package dk.fitfit.liftlog.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import dk.fitfit.liftlog.service.ConfigurationServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Component
public class GoogleAuth {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final ConfigurationServiceInterface configurationService;

	@Autowired
	public GoogleAuth(ConfigurationServiceInterface configurationService) {
		this.configurationService = configurationService;
	}

	public String getSub(String idTokenString) {
			GoogleIdToken.Payload payload = getPayload(idTokenString);
			return payload.getSubject();
	}

	public String getEmail(String idTokenString) {
		GoogleIdToken.Payload payload = getPayload(idTokenString);
		return payload.getEmail();
	}

	public String getUsername(String idTokenString) {
		GoogleIdToken.Payload payload = getPayload(idTokenString);
		return (String) payload.get("name");
	}

	// TODO: Handle exceptions!!!
	private GoogleIdToken.Payload getPayload(String idTokenString) {
		GoogleIdTokenVerifier verifier = getTokenVerifier();
		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(idTokenString);
		} catch (GeneralSecurityException | IOException e) {
			log.debug(e.getMessage());
		}
		if (idToken == null) {
			throw new IllegalArgumentException("Bad token...");
		} else {
			return idToken.getPayload();
		}
	}

	private GoogleIdTokenVerifier getTokenVerifier() {
		NetHttpTransport transport = new NetHttpTransport();
		return new GoogleIdTokenVerifier.Builder(transport, new JacksonFactory())
				.setAudience(Collections.singletonList(configurationService.getClientId()))
				.build();
	}

}
