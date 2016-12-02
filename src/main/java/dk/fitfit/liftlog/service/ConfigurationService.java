package dk.fitfit.liftlog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService implements ConfigurationServiceInterface {
	@Value("${security.oauth2.client.clientId}")
	private String clientId;

	@Override
	public String getClientId() {
		return clientId;
	}
}
