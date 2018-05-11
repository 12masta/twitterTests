package com.ms.twittertests.api;

import com.ms.twittertests.credentials.User;
import com.ms.twittertests.http.HttpClient;
import com.ms.twittertests.http.HttpData;
import com.ms.twittertests.mapper.Mapper;
import com.ms.twittertests.models.Tweet;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import java.io.IOException;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class TweetTimeline {
    private HttpData httpData;
    private HttpClient httpClient;

    public TweetTimeline(HttpData httpData, HttpClient httpClient) {
        this.httpData = httpData;
        this.httpClient = httpClient;
    }

    public List<Tweet> getUserTimelineAwaitable(User user, int expectedNumber) throws IOException, ClassNotFoundException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        await().atMost(30, SECONDS).until(() -> getUserTimeline(user).size() == expectedNumber);
        return getUserTimeline(user);
    }

    public List<Tweet> getUserTimeline(User user) throws IOException, ClassNotFoundException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        String json = httpClient.get(httpData.getBaseApiUrl() + "/" + httpData.getVersion() + "/statuses/user_timeline.json?user_id=" + user.getTweeterId());
        return Mapper.parseJsonArray(json, Tweet.class);
    }

    public void removeTweets(User user) throws ClassNotFoundException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        List<Tweet> tweets = getUserTimeline(user);
        for (Tweet tweet : tweets) {
            removeTweet(tweet);
        }
    }

    private void removeTweet(Tweet tweet) throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        httpClient.post(httpData.getBaseApiUrl() + "/" + httpData.getVersion() + "/statuses/destroy/" + tweet.getIdStr() + ".json", "");
    }
}
