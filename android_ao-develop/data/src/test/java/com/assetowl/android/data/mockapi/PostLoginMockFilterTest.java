package com.assetowl.android.data.mockapi;

import android.content.Context;
import android.util.Log;

import com.assetowl.android.data.login.model.UserDataCredentials;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import okhttp3.CipherSuite;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.TlsVersion;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by patrickyin on 27/3/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Gson.class, Request.class, Log.class})
public class PostLoginMockFilterTest {
    @Mock
    Context context;
    @Mock
    Gson gson;
    @Mock
    Request request;
    @Mock
    RequestBody requestBody;

    private PostLoginMockFilter postLoginMockFilter;

    @Before
    public void setUp() throws Exception {
        mockStatic(Log.class);
        when(request.body())
                .thenReturn(requestBody);
        postLoginMockFilter = new PostLoginMockFilter(context, gson);
    }

    @Test
    public void shouldReturnSuccessLoginResponseString() throws Exception {
        when(gson.fromJson(any(String.class),eq(UserDataCredentials.class)))
                .thenReturn(PostLoginMockFilter.SUCCESS_LOGIN_USER);
        postLoginMockFilter.setRequest(request);
        assertTrue(postLoginMockFilter.getResponseString().contains("firstName"));
    }

    @Test
    public void shouldReturn50XLoginResponseString() throws Exception {
        when(gson.fromJson(any(String.class),eq(UserDataCredentials.class)))
                .thenReturn(PostLoginMockFilter.ERROR_500_LOGIN_USER);
        postLoginMockFilter.setRequest(request);
        assertEquals(500, postLoginMockFilter.buildResponse().code());
        when(gson.fromJson(any(String.class),eq(UserDataCredentials.class)))
                .thenReturn(PostLoginMockFilter.ERROR_501_LOGIN_USER);
        postLoginMockFilter.setRequest(request);
        assertEquals(501, postLoginMockFilter.buildResponse().code());
        when(gson.fromJson(any(String.class),eq(UserDataCredentials.class)))
                .thenReturn(PostLoginMockFilter.ERROR_502_LOGIN_USER);
        postLoginMockFilter.setRequest(request);
        assertEquals(502, postLoginMockFilter.buildResponse().code());
        when(gson.fromJson(any(String.class),eq(UserDataCredentials.class)))
                .thenReturn(PostLoginMockFilter.ERROR_503_LOGIN_USER);
        postLoginMockFilter.setRequest(request);
        assertEquals(503, postLoginMockFilter.buildResponse().code());
        when(gson.fromJson(any(String.class),eq(UserDataCredentials.class)))
                .thenReturn(PostLoginMockFilter.ERROR_504_LOGIN_USER);
        postLoginMockFilter.setRequest(request);
        assertEquals(504, postLoginMockFilter.buildResponse().code());
    }


    @Test
    public void shouldReturnInvalidUsernameOrPasswordResponseString() throws Exception {
        when(gson.fromJson(any(String.class),eq(UserDataCredentials.class)))
                .thenReturn(new UserDataCredentials("asdasdf34tfeds@asdaf34ewdc.com", "Se43fgg34cr4etOwl"));
        postLoginMockFilter.setRequest(request);
        assertEquals(401, postLoginMockFilter.buildResponse().code());
        assertTrue(postLoginMockFilter.getResponseString().contains("message"));
    }


    @Test
    public void shouldReturnCertificateErrorResponse() throws Exception {
        when(gson.fromJson(any(String.class),eq(UserDataCredentials.class)))
                .thenReturn(PostLoginMockFilter.ERROR_CERTIFICATE_LOGIN_USER);
        postLoginMockFilter.setRequest(request);
        Response response = postLoginMockFilter.getCustomResponse();
        assertEquals(495, response.code());
        assertNull(response.handshake().peerPrincipal());
        assertNull(response.handshake().localPrincipal());
        assertTrue(response.handshake().localCertificates().isEmpty());
        assertTrue(response.handshake().peerCertificates().isEmpty());
        assertEquals(response.handshake().tlsVersion(), TlsVersion.TLS_1_2);
    }

    @Test
    public void shouldReturnTimeoutResponse() throws Exception {
        when(gson.fromJson(any(String.class),eq(UserDataCredentials.class)))
                .thenReturn(PostLoginMockFilter.ERROR_TIMEOUT_LOGIN_USER);
        postLoginMockFilter.setRequest(request);
        long startTime = new Date().getTime();
        Response response = postLoginMockFilter.getCustomResponse();
        assertTrue(new Date().getTime() >= startTime + 5_000);
        assertEquals(504, response.code());
    }

}