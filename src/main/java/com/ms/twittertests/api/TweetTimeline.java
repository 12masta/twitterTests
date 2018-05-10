package com.ms.twittertests.api;

import com.ms.twittertests.credentials.User;
import com.ms.twittertests.http.HttpClient;
import com.ms.twittertests.mapper.Mapper;
import com.ms.twittertests.models.Tweet;

import java.io.IOException;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class TweetTimeline {
    private HttpClient httpClient;

    public TweetTimeline(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<Tweet> getUserTimelineAwaitable(User user, int expectedNumber) throws IOException, ClassNotFoundException, InterruptedException {
        await().atMost(30, SECONDS).until(() -> getUserTimeline(user).size() == expectedNumber);
        return getUserTimeline(user);
    }

    public List<Tweet> getUserTimeline(User user) throws IOException, ClassNotFoundException {
        String json = httpClient.get("https://api.twitter.com/1.1/statuses/user_timeline.json?user_id=" + user.getTweeterId());
        return Mapper.parseJsonArray(json, Tweet.class);
    }
}
