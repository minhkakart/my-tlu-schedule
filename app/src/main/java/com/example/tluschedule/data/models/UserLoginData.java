package com.example.tluschedule.data.models;

public class UserLoginData extends JsonModelBase {
    private final String username;
    private final String password;
    private final String grant_type;
    private final String client_id;
    private final String client_secret;

    public UserLoginData(String username, String password) {
        this.username = username;
        this.password = password;
        this.grant_type = "password";
        this.client_id = "education_client";
        this.client_secret = "password";
    }

    public UserLoginData(String username, String password, String grant_type, String client_id, String client_secret) {
        this.username = username;
        this.password = password;
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }
}
