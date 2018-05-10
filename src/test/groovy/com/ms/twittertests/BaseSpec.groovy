package com.ms.twittertests

import com.ms.twittertests.api.TweetTimeline
import com.ms.twittertests.credentials.User
import com.ms.twittertests.credentials.UserImpl
import com.ms.twittertests.data.DummyUser
import com.ms.twittertests.http.HttpClientImpl
import com.ms.twittertests.http.OauthResolver
import com.ms.twittertests.pages.HomePage
import com.ms.twittertests.pages.LogInPageWebImpl
import io.github.bonigarcia.wdm.WebDriverManager
import okhttp3.OkHttpClient
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.TimeUnit

class BaseSpec extends Specification {
    WebDriver driver

    @Shared
    User validUser = new UserImpl(
            DummyUser.BASE_USER.login,
            DummyUser.BASE_USER.password,
            DummyUser.BASE_USER.tweeterId,
            DummyUser.BASE_USER.name)

    def tweetTimeline = new TweetTimeline(new HttpClientImpl(new OauthResolver(), new OkHttpClient()))

    @Shared
            generator = { int n ->
                new Random().with {
                    String alphabet = (('A'..'Z') + ('0'..'9')).join()
                    (1..n).collect { alphabet[nextInt(alphabet.length())] }.join()
                }
            }

    def setupSpec() {
        WebDriverManager.chromedriver().setup()
    }

    def setup() {
        driver = new ChromeDriver()
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage().window().maximize()
        driver.get("https://twitter.com/login")
    }

    def cleanup() {
        if (driver != null) {
            driver.quit()
        }
    }

    HomePage logIn(User user) {
        new LogInPageWebImpl(driver)
                .logIn(user)
    }
}