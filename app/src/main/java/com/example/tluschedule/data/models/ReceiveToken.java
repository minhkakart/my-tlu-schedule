package com.example.tluschedule.data.models;

public class ReceiveToken {
    private final String access_token;
    private final String token_type;
    private final String refresh_token;
    private final String error;
    private final int expires_in;
    private final String scope;

    public ReceiveToken(String access_token, String token_type, String refresh_token, int expires_in, String scope, String error) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.scope = scope;
        this.error = error;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getError() {
        return error;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public String getScope() {
        return scope;
    }
}
