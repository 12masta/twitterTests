package com.ms.twittertests.api;

import com.ms.twittertests.http.HttpClient;
import com.ms.twittertests.http.HttpData;
import com.ms.twittertests.mapper.Mapper;
import com.ms.twittertests.models.Tweet;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import java.io.IOException;
import java.util.List;

public class TweetFavorites {
    private HttpData httpData;
    private HttpClient httpClient;

    public TweetFavorites(HttpData httpData, HttpClient httpClient) {
        this.httpData = httpData;
        this.httpClient = httpClient;
    }

    public void destroyTweetsLikes() throws IOException, ClassNotFoundException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        List<Tweet> tweets = getTweetLikes();
        for (Tweet tweet : tweets) {
            destroyTweetLikes(tweet);
        }
    }

    public List<Tweet> getTweetLikes() throws IOException, ClassNotFoundException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        String json = httpClient.get(httpData.getBaseApiUrl() + "/" + httpData.getVersion() + "/favorites/list.json");
        return Mapper.parseJsonArray(json, Tweet.class);
    }

    private void destroyTweetLikes(Tweet tweet) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        httpClient.post(httpData.getBaseApiUrl() + "/" + httpData.getVersion() + "/favorites/destroy.json?id=" + tweet.getIdStr(), "");
    }
}
