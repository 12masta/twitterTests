package com.ms.twittertests.pages;

public interface HomePage {
    boolean isHomePage();

    HomePage postTweetUsingTimeline(String tweetBody);

    boolean isTweetShownOnTheTopOfTheTimeline(String tweetBody);

    HomePage postTweetUsingNavBar(String tweetBody);

    boolean getIsTweetWasSendModalShown();
}
