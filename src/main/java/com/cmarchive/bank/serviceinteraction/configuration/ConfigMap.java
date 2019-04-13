package com.cmarchive.bank.serviceinteraction.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.oauth2.client")
public class ConfigMap {

    private String clientId;
    private String clientSecret;
    private String accessTokenUri;
    private String userAuthorizationUri;
    private String preEstablishedRedirectUri;

    public String getClientId() {
        return clientId;
    }

    public ConfigMap setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public ConfigMap setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public String getAccessTokenUri() {
        return accessTokenUri;
    }

    public ConfigMap setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
        return this;
    }

    public String getUserAuthorizationUri() {
        return userAuthorizationUri;
    }

    public ConfigMap setUserAuthorizationUri(String userAuthorizationUri) {
        this.userAuthorizationUri = userAuthorizationUri;
        return this;
    }

    public String getPreEstablishedRedirectUri() {
        return preEstablishedRedirectUri;
    }

    public ConfigMap setPreEstablishedRedirectUri(String preEstablishedRedirectUri) {
        this.preEstablishedRedirectUri = preEstablishedRedirectUri;
        return this;
    }
}
