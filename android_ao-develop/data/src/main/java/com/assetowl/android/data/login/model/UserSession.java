package com.assetowl.android.data.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by patrickyin on 14/2/17.
 */

public class UserSession {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("accessTokenExpiry")
    @Expose
    private Long accessTokenExpiry;
    @SerializedName("tosAccepted")
    @Expose
    private boolean tosAccepted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getAccessTokenExpiry() {
        return accessTokenExpiry;
    }

    public void setAccessTokenExpiry(Long accessTokenExpiry) {
        this.accessTokenExpiry = accessTokenExpiry;
    }

    public boolean getTosAccepted() {
        return tosAccepted;
    }

    public void setTosAccepted(boolean tosAccepted) {
        this.tosAccepted = tosAccepted;
    }
}
