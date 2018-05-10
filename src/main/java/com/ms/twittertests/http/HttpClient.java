package com.ms.twittertests.http;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import java.io.IOException;

public interface HttpClient {
    String get(String url) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException;

    String post(String url, String json) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException;
}
