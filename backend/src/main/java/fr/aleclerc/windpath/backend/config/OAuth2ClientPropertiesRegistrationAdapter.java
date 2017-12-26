package fr.aleclerc.windpath.backend.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.bind.convert.BinderConversionService;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class OAuth2ClientPropertiesRegistrationAdapter {
    private OAuth2ClientPropertiesRegistrationAdapter() {
    }

    public static Map<String, ClientRegistration> getClientRegistrations(OAuth2ClientProperties properties) {
        Map<String, ClientRegistration> clientRegistrations = new HashMap();
        properties.getRegistration().forEach((key, value) -> {
            ClientRegistration var10000 = (ClientRegistration)clientRegistrations.put(key, getClientRegistration(key, value, properties.getProvider()));
        });
        return clientRegistrations;
    }

    private static ClientRegistration getClientRegistration(String registrationId, OAuth2ClientProperties.Registration properties, Map<String, OAuth2ClientProperties.Provider> providers) {
        ClientRegistration.Builder builder = getBuilder(registrationId, properties.getProvider(), providers);
        copyIfNotNull(properties::getClientId, builder::clientId);
        copyIfNotNull(properties::getClientSecret, builder::clientSecret);
        copyIfNotNull(properties::getClientAuthenticationMethod, builder::clientAuthenticationMethod, ClientAuthenticationMethod::new);
        copyIfNotNull(properties::getAuthorizationGrantType, builder::authorizationGrantType, AuthorizationGrantType::new);
        copyIfNotNull(properties::getRedirectUriTemplate, builder::redirectUriTemplate);
        copyIfNotNull(properties::getScope, builder::scope, (scope) -> {
            return (String[])scope.toArray(new String[scope.size()]);
        });
        copyIfNotNull(properties::getClientName, builder::clientName);
        return builder.build();
    }

    private static ClientRegistration.Builder getBuilder(String registrationId, String configuredProviderId, Map<String, OAuth2ClientProperties.Provider> providers) {
        String providerId = configuredProviderId == null ? registrationId : configuredProviderId;
        CommonOAuth2Provider provider = getCommonProvider(providerId);
        if (provider == null && !providers.containsKey(providerId)) {
            throw new IllegalStateException(getErrorMessage(configuredProviderId, registrationId));
        } else {
            ClientRegistration.Builder builder = provider != null ? provider.getBuilder(registrationId) : ClientRegistration.withRegistrationId(registrationId);
            return providers.containsKey(providerId) ? getBuilder(builder, (OAuth2ClientProperties.Provider)providers.get(providerId)) : builder;
        }
    }

    private static String getErrorMessage(String configuredProviderId, String registrationId) {
        return configuredProviderId == null ? "Provider ID must be specified for client registration '" + registrationId + "'" : "Unknown provider ID '" + configuredProviderId + "'";
    }

    private static ClientRegistration.Builder getBuilder(ClientRegistration.Builder builder, OAuth2ClientProperties.Provider provider) {
        copyIfNotNull(provider::getAuthorizationUri, builder::authorizationUri);
        copyIfNotNull(provider::getTokenUri, builder::tokenUri);
        copyIfNotNull(provider::getUserInfoUri, builder::userInfoUri);
        copyIfNotNull(provider::getJwkSetUri, builder::jwkSetUri);
        return builder;
    }

    private static CommonOAuth2Provider getCommonProvider(String providerId) {
        try {
            return (CommonOAuth2Provider)(new BinderConversionService((ConversionService)null)).convert(providerId, CommonOAuth2Provider.class);
        } catch (ConversionException var2) {
            return null;
        }
    }

    private static <T> void copyIfNotNull(Supplier<T> supplier, Consumer<T> consumer) {
        copyIfNotNull(supplier, consumer, Function.identity());
    }

    private static <S, C> void copyIfNotNull(Supplier<S> supplier, Consumer<C> consumer, Function<S, C> converter) {
        S value = supplier.get();
        if (value != null) {
            consumer.accept(converter.apply(value));
        }

    }
}