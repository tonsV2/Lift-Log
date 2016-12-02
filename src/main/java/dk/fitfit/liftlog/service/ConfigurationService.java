package dk.fitfit.liftlog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
	@Value("${security.oauth2.client.clientId}")
	private String clientId;

	public String getClientId() {
		return clientId;
	}
}
