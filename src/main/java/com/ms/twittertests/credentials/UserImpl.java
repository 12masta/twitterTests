package com.ms.twittertests.credentials;

public class UserImpl implements User {
    private String login;
    private String password;

    public UserImpl(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
