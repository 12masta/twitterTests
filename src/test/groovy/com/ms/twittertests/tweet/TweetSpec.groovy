package com.ms.twittertests.tweet

import com.ms.twittertests.BaseSpec
import spock.lang.Unroll

import java.lang.Void as TestCase

class TweetSpec extends BaseSpec {
    @Unroll
    TestCase "User post a tweet using timeline"() {
        when: "user log in"
        def homePage = logIn user
        and: "post a tweet using timeline"
        homePage.postTweetUsingTimeline tweetBody

        then: "tweet shown on the top of the timeline"
        homePage.isTweetShownOnTheTopOfTheTimeline tweetBody

        where:
        tweetBody    | user
        generator(9) | validUser
    }

    @Unroll
    TestCase "User post a tweet using navbar"() {
        when: "user log in"
        def homePage = logIn user
        and: "post a tweet using timeline"
        homePage.postTweetUsingNavBar tweetBody

        then: "tweet shown on the top of the timeline"
        homePage.isTweetWasSendModalShown

        where:
        tweetBody     | user
        generator(10) | validUser
    }

    @Unroll
    TestCase "User post a tweet using timeline and tweet appears in the api"() {
        given: "timeline has specified size"
        def numbersOfTweetsBefore = tweetTimeline.getUserTimeline(user).size()

        when: "user log in"
        def homePage = logIn user
        and: "post a tweet using timeline"
        homePage.postTweetUsingTimeline tweetBody

        then: "the number of tweets is higher by 1"
        def tweets = tweetTimeline.getUserTimelineAwaitable user, numbersOfTweetsBefore + 1
        tweets.size() == numbersOfTweetsBefore + 1
        and: "tweet has correct body"
        tweets.first().text == tweetBody
        and: "tweet has a proper author"
        tweets.first().user.name == validUser.name

        where:
        tweetBody    | user
        generator(9) | validUser
    }

    @Unroll
    TestCase "User post a tweet using navbar and tweet appears in the api"() {
        given: "timeline has specified size"
        def numbersOfTweetsBefore = tweetTimeline.getUserTimeline(user).size()

        when: "user log in"
        def homePage = logIn user
        and: "post a tweet using timeline"
        homePage.postTweetUsingNavBar tweetBody

        then: "the number of tweets is higher by 1"
        def tweets = tweetTimeline.getUserTimelineAwaitable user, numbersOfTweetsBefore + 1
        tweets.size() == numbersOfTweetsBefore + 1
        and: "tweet has correct body"
        tweets.first().text == tweetBody
        and: "tweet has a proper author"
        tweets.first().user.name == validUser.name

        where:
        tweetBody     | user
        generator(10) | validUser
    }
}
