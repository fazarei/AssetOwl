package com.assetowl.domain.utils.exception;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by patrickyin on 16/3/17.
 */
public class AssetOwlExceptionTest {
    @Test
    public void getErrorCode() throws Exception {
        AssetOwlException assetOwlException = new AssetOwlException("", AssetOwlException.AUTH_INVALID_USERNAME_OR_PASSWORD);
        assertEquals(assetOwlException.getErrorCode(), AssetOwlException.AUTH_INVALID_USERNAME_OR_PASSWORD);
    }

    @Test
    public void isNetworkError() throws Exception {
        AssetOwlException assetOwlException = new AssetOwlException("", AssetOwlException.NETWORK_TIME_OUT);
        assertTrue(assetOwlException.isNetworkError());
        assetOwlException = new AssetOwlException("", AssetOwlException.NETWORK_ISSUE);
        assertTrue(assetOwlException.isNetworkError());
        assetOwlException = new AssetOwlException("", AssetOwlException.AUTH_INVALID_USERNAME_OR_PASSWORD);
        assertFalse(assetOwlException.isNetworkError());
    }

    @Test
    public void isInvalidUsernameOrPasswordError() throws Exception {
        AssetOwlException assetOwlException = new AssetOwlException("", AssetOwlException.NETWORK_ISSUE);
        assertFalse(assetOwlException.isInvalidUsernameOrPasswordError());
        assetOwlException = new AssetOwlException("", AssetOwlException.AUTH_INVALID_USERNAME_OR_PASSWORD);
        assertTrue(assetOwlException.isInvalidUsernameOrPasswordError());

    }

}