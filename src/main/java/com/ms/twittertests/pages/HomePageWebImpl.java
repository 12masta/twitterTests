package com.ms.twittertests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageWebImpl extends BasePage implements HomePage {
    private int numberOfLiElements;
    private By listElementsLocator = By.tagName("li");

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
    public boolean isTweetShownOnTheTopOfTheTimeline(String tweetBody) {
        waitForListUpdate();
        return timeline.findElements(listElementsLocator).get(0).findElement(By.tagName("p")).getText().equals(tweetBody);
    }

    private void waitForListUpdate() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(listElementsLocator, numberOfLiElements));
    }
}
