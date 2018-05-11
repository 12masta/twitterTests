package com.ms.twittertests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;

public class HomePageWebImpl extends BasePage implements HomePage {
    private int numberOfLiElements;
    private By listElementsLocator = By.xpath(".//li[@data-item-type='tweet']");

    public HomePageWebImpl(WebDriver driver) {
        super(driver);
        numberOfLiElements = driver.findElements(listElementsLocator).size();
    }

    @FindBy(xpath = "//div[@class='timeline-tweet-box']//div[@id='tweet-box-home-timeline']")
    private WebElement tweetBoxHomeTimelineInput;

    @FindBy(xpath = "//div[@class='timeline-tweet-box']//div[@class='TweetBoxToolbar']//button[contains(@class, 'tweet-action')]")
    private WebElement tweetButton;

    @FindBy(id = "stream-items-id")
    private WebElement timeline;

    @FindBy(id = "global-new-tweet-button")
    private WebElement globalNewTweetButton;

    @FindBy(xpath = "//div[@id='Tweetstorm-tweet-box-0']//div[@name='tweet']")
    private WebElement tweetstormTweetBoxInput;

    @FindBy(xpath = "//div[@id='Tweetstorm-tweet-box-0']//div[@class='TweetBoxToolbar']//button[contains(@class, 'SendTweetsButton')]")
    private WebElement tweetstormTweetButton;

    @FindBy(id = "message-drawer")
    private WebElement messageDrawer;

    @Override
    public boolean isHomePage() {
        return driver.getTitle().equals("Twitter");
    }

    @Override
    public HomePage postTweetUsingTimeline(String tweetBody) {
        tweetBoxHomeTimelineInput.click();
        tweetBoxHomeTimelineInput.sendKeys(tweetBody);
        tweetButton.click();
        return this;
    }

    @Override
    public HomePage postTweetUsingNavBar(String tweetBody) {
        globalNewTweetButton.click();
        tweetstormTweetBoxInput.sendKeys(tweetBody);
        tweetstormTweetButton.click();
        return this;
    }

    @Override
    public boolean getIsTweetWasSendModalShown() {
        waitForModal(By.id("message-drawer"), "class", "alert-messages js-message-drawer-visible");
        return messageDrawer.isDisplayed();
    }

    @Override
    public String likeTweet(int number) {
        WebElement tweet =  timeline.findElements(listElementsLocator).get(number);
        tweet.findElement(By.xpath(".//button[@class='ProfileTweet-actionButton js-actionButton js-actionFavorite']")).click();
        return tweet.getAttribute("id");
    }

    @Override
    public boolean isTweetLiked(String id) {
        String likedButtonLocator = "ProfileTweet-action ProfileTweet-action--favorite js-toggleState";
        Optional<WebElement> webElement = timeline.findElements(listElementsLocator).stream()
                .filter(x -> x.getAttribute("id").equals(id))
                .findFirst();
        return webElement.get().findElements(By.xpath(".//div[@class='" + likedButtonLocator + "']")).size() > 0;
    }

    @Override
    public boolean isTweetShownOnTheTopOfTheTimeline(String tweetBody) {
        waitForListUpdate();
        return timeline.findElements(listElementsLocator).get(0).findElement(By.tagName("p")).getText().equals(tweetBody);
    }

    private void waitForModal(By locator, String attribute, String attributeValue){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeContains(locator, attribute, attributeValue));
    }

    private void waitForListUpdate() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(listElementsLocator, numberOfLiElements));
    }
}
