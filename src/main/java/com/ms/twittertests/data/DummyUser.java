package com.ms.twittertests.data;

public enum DummyUser {
    BASE_USER("h1040567@nwytg.com", "onhBKE50nhZ2ucv3UPSf", "993992099849736193", "Andrzej Testowy");

    private String login;
    private String password;
    private String tweeterId;
    private String name;

    DummyUser(String login, String password, String tweeterId, String name){
        this.login = login;
        this.password = password;
        this.tweeterId = tweeterId;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getTweeterId() {
        return tweeterId;
    }

    public String getName() {
        return name;
    }
}
