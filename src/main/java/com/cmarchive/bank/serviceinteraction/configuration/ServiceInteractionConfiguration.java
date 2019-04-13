package com.cmarchive.bank.serviceinteraction.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
public class ServiceInteractionConfiguration {

    private ConfigMap configMap;

    public ServiceInteractionConfiguration(ConfigMap configMap) {
        this.configMap = configMap;
    }

    @Bean
    public OAuth2ProtectedResourceDetails resource() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("Authorization");
        details.setClientId(configMap.getClientId());
        details.setClientSecret(configMap.getClientSecret());
        details.setAccessTokenUri(configMap.getAccessTokenUri());
        details.setUserAuthorizationUri(configMap.getUserAuthorizationUri());
        details.setTokenName("oauth_token");
        details.setScope(Arrays.asList("user_info"));
        details.setPreEstablishedRedirectUri(configMap.getPreEstablishedRedirectUri());
        details.setUseCurrentUri(true);

        return details;
    }

    @Bean
    public RestTemplate restTemplate(OAuth2ClientContext clientContext) {

        return new OAuth2RestTemplate(resource(), clientContext);
    }

}
