package fr.aleclerc.windpath.backend.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.ArrayList;


@Configuration
@EnableConfigurationProperties({OAuth2ClientProperties.class})
public class OAuthConfig {
    private final OAuth2ClientProperties properties;

    public OAuthConfig(OAuth2ClientProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean({ClientRegistrationRepository.class})
    public InMemoryClientRegistrationRepository clientRegistrationRepository() {
        ArrayList<ClientRegistration> registrations = new ArrayList(OAuth2ClientPropertiesRegistrationAdapter.getClientRegistrations(this.properties).values());
        return new InMemoryClientRegistrationRepository(registrations);
    }

}
