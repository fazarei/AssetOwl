package com.assetowl.android.data.login.model.realm;

import io.realm.RealmObject;

/**
 * Created by farzanehzarei on 20/3/17.
 */

public class LocalSessionInfo extends RealmObject {

    private String refreshToken;
    private String accessToken;
    private Long accessTokenExpiry;
    private boolean tokenResult;
    private String firstName;
    private String lastName;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getAccessTokenExpiry() {
        return accessTokenExpiry;
    }

    public void setAccessTokenExpiry(Long accessTokenExpiry) {
        this.accessTokenExpiry = accessTokenExpiry;
    }

    public boolean getTokenResult() {
        return tokenResult;
    }

    public void setTokenResult(boolean tokenResult) {
        this.tokenResult = tokenResult;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
