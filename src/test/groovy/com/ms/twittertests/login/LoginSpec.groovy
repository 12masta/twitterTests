package com.ms.twittertests.login

import com.ms.twittertests.BaseSpec
import java.lang.Void as TestCase

import spock.lang.Unroll

class LoginSpec extends BaseSpec {
    @Unroll
    TestCase "User logged in"() {
        when: "user log in"
        def homePage = logIn user

        then: "home page is loaded"
        homePage.isHomePage()

        where:
        user      | _
        validUser | _
    }
}
