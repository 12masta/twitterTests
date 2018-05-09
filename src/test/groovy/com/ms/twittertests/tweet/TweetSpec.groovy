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
        randomString | validUser
    }

    @Unroll
    TestCase "User post a tweet using navbar"() {
        when: "user log in"
        def homePage = logIn user
        and: "post a tweet using timeline"
        homePage.postTweetUsingNavBar tweetBody

        then: "tweet shown on the top of the timeline"
        homePage.isTweetShownOnTheTopOfTheTimeline tweetBody

        where:
        tweetBody    | user
        randomString | validUser
    }
}
