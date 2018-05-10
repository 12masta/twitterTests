package com.ms.twittertests.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpClientImpl implements HttpClient{
    private OkHttpClient client;
    private OauthResolver oauthResolver;

    public HttpClientImpl(OauthResolver oauthResolver, OkHttpClient client) {
        this.client = client;
        this.oauthResolver = oauthResolver;
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader(oauthResolver.getHeaderName(), oauthResolver.getHeaderValue())
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
