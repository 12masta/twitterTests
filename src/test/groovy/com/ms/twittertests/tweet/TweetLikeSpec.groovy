package com.ms.twittertests.tweet

import com.ms.twittertests.BaseSpec
import spock.lang.Unroll

class TweetLikeSpec extends BaseSpec {
    @Unroll
    Void "User likes a tweet"() {
        given: "user has empty favorites tweet list"
        tweetFavorites.destroyTweetsLikes()

        when: "user log in"
        def homePage = logIn user
        and: "like a post at the of of the timeline"
        def id = homePage.likeTweet tweetNumber

        then: "tweet is liked by the user"
        homePage.isTweetLiked id

        where:
        tweetNumber | user
        1           | validUser
        2           | validUser
    }
}
