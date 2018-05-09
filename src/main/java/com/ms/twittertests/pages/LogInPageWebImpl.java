package com.ms.twittertests.pages;

import com.ms.twittertests.credentials.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPageWebImpl extends BasePage implements LogInPage {
    public LogInPageWebImpl(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='page-container']//input[@name='session[username_or_email]']")
    private WebElement loginInput;

    @FindBy(xpath = "//div[@id='page-container']//input[@name='session[password]']")
    private WebElement passwordInput;

    @FindBy(xpath = "//div[@id='page-outer']//button[@type='submit']")
    private WebElement logInButton;

    @Override
    public HomePage logIn(User user) {
        loginInput.sendKeys(user.getLogin());
        passwordInput.sendKeys(user.getPassword());
        logInButton.click();
        return new HomePageWebImpl(driver);
    }
}
