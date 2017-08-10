package com.assetowl.domain.session.model;

/**
 * Created by jamespott on 7/2/17.
 */

public interface SessionInfo {
    public String getAccessToken();

    public String getRefreshToken();

    public Long getAccessTokenExpiry();

    public String getEmail();

    public boolean getTosAccepted();

    public String getFirstName();

    public String getLastName();
}
