package com.ms.twittertests.http;

public class HttpData {
    private String baseUrl;
    private String baseApiUrl;
    private String version;
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;

    public HttpData(String baseUrl, String baseApiUrl, String version, String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        this.baseUrl = baseUrl;
        this.baseApiUrl = baseApiUrl;
        this.version = version;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBaseApiUrl() {
        return baseApiUrl;
    }

    public String getVersion() {
        return version;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }
}