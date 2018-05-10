package com.ms.twittertests.credentials;

public class UserImpl implements User {
    private String login;
    private String password;
    private String tweeterId;
    private String name;

    public UserImpl(String login, String password, String tweeterId, String name) {
        this.login = login;
        this.password = password;
        this.tweeterId = tweeterId;
        this.name = name;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getTweeterId() {
        return tweeterId;
    }

    public String getName() {
        return name;
    }
}
