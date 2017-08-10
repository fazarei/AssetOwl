package com.assetowl.android.data.login.model;

/**
 * Created by farzanehzarei on 7/4/17.
 */

public class TermAndConditionCredentials {

    public String ACCESS_TOKEN_PREFIX="Bearer ";
    public String accessToken="";
    public String username;
    public int versionNumber;



    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = ACCESS_TOKEN_PREFIX + accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }
}