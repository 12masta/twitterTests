package com.ms.twittertests.pages;

import org.openqa.selenium.WebDriver;

public class HomePageWebImpl extends BasePage implements HomePage {
    public HomePageWebImpl(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isHomePage() {
        return driver.getTitle().equals("Twitter");
    }
}
