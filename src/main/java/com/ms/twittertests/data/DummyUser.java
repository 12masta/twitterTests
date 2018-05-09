package com.ms.twittertests.data;

public enum DummyUser {
    BASE_USER("h1040567@nwytg.com", "onhBKE50nhZ2ucv3UPSf");

    private String login;
    private String password;

    DummyUser(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
