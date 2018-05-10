package com.ms.twittertests.http;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;

import java.io.IOException;

public class HttpClientImpl implements HttpClient {
    private OkHttpClient client;
    private HttpData httpData;

    public HttpClientImpl(HttpData httpData, OkHttpClient client) {
        this.client = client;
        this.httpData = httpData;
    }

    public String get(String url) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Request signedRequest = (Request) getConsumer().sign(request).unwrap();
        Response response = client.newCall(signedRequest).execute();
        return response.body().string();
    }

    @Override
    public String post(String url, String json) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        RequestBody body = RequestBody.create(null, new byte[0]);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .build();
        Request signedRequest = (Request) getConsumer().sign(request).unwrap();
        Response response = client.newCall(signedRequest).execute();
        return response.body().string();
    }

    private OkHttpOAuthConsumer getConsumer() {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(httpData.getConsumerKey(), httpData.getConsumerSecret());
        consumer.setTokenWithSecret(httpData.getAccessToken(), httpData.getAccessTokenSecret());
        return consumer;
    }
}
