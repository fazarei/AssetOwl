package com.assetowl.domain.utils.exception;

/**
 * Created by patrickyin on 15/3/17.
 */

public final class AssetOwlException extends Exception {
    private final int code;
    private int httpCode;
    public final static int AUTH_INVALID_USERNAME_OR_PASSWORD = 600;
    public final static int NETWORK_ISSUE = 601;
    public final static int NETWORK_TIME_OUT = 602;

    public AssetOwlException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getErrorCode() {
        return code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public boolean isNetworkError() {
        return code == NETWORK_ISSUE || code == NETWORK_TIME_OUT;
    }

    public boolean isInvalidUsernameOrPasswordError() {
        return code == AUTH_INVALID_USERNAME_OR_PASSWORD;
    }
}
