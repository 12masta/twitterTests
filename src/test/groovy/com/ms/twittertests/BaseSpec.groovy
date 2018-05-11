package com.ms.twittertests

import com.anotherchrisberry.spock.extensions.retry.RetryOnFailure
import com.ms.twittertests.api.TweetFavorites
import com.ms.twittertests.api.TweetTimeline
import com.ms.twittertests.credentials.User
import com.ms.twittertests.credentials.UserImpl
import com.ms.twittertests.http.HttpClientImpl
import com.ms.twittertests.http.HttpData
import com.ms.twittertests.pages.HomePage
import com.ms.twittertests.pages.LogInPageWebImpl
import io.github.bonigarcia.wdm.WebDriverManager
import okhttp3.OkHttpClient
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.TimeUnit

@RetryOnFailure(times=3)
class BaseSpec extends Specification {
    WebDriver driver

    @Shared
    TweetTimeline tweetTimeline

    @Shared
    TweetFavorites tweetFavorites

    @Shared
    def configuration

    @Shared
    User validUser

    @Shared
            generator = { int n ->
                new Random().with {
                    String alphabet = (('A'..'Z') + ('0'..'9')).join()
                    (1..n).collect { alphabet[nextInt(alphabet.length())] }.join()
                }
            }

    def setupSpec() {
        configuration = getConfig()
        def httpData = new HttpData(
                configuration.baseUrl,
                configuration.baseApiUrl,
                configuration.apiVersion,
                configuration.consumerKey,
                configuration.consumerSecret,
                configuration.accessToken,
                configuration.accessTokenSecret)
        validUser = new UserImpl(
                configuration.login,
                configuration.password,
                configuration.tweeterId,
                configuration.name)
        tweetTimeline = new TweetTimeline(httpData, new HttpClientImpl(httpData, new OkHttpClient()))
        tweetFavorites = new TweetFavorites(httpData, new HttpClientImpl(httpData, new OkHttpClient()))
        WebDriverManager.chromedriver().setup()
    }

    def setup() {
        driver = new ChromeDriver()
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage().window().maximize()
        driver.get(configuration.baseUrl + "/login")
    }

    def cleanup() {
        if (driver != null) {
            driver.quit()
        }
    }

    def getConfig() {
        new ConfigSlurper("production").parse(getClass().getResource("/Config.groovy").toURI().toURL())
    }

    HomePage logIn(User user) {
        new LogInPageWebImpl(driver)
                .logIn(user)
    }
}