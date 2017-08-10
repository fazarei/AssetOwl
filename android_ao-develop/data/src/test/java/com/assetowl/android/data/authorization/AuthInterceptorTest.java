package com.assetowl.android.data.authorization;

import com.assetowl.android.data.localstorage.SessionLocalData;
import com.assetowl.android.data.login.model.realm.LocalSessionInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by patrickyin on 22/5/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SessionLocalData.class, Request.class, Headers.class})
public class AuthInterceptorTest {
    @Mock
    Interceptor.Chain chain;
    @Mock
    Request request;
    @Mock
    Request.Builder builder;
    @Mock
    LocalSessionInfo sessionInfo;
    @Mock
    Headers headers;
    @Mock
    Set<String> headerNames;

    private AuthInterceptor authInterceptor;

    private final static String HEADER_AUTH_KEY = "Authorization";
    private final static String HEADER_AUTH_VALUE_PREFIX = "Bearer ";
    private final static String HEADER_AUTH_VALUE = "accessToken";

    @Before
    public void setUp() throws Exception {
        when(chain.request()).thenReturn(request);
        when(request.newBuilder()).thenReturn(builder);
        when(builder.build()).thenReturn(request);
        when(builder.header(anyString(), anyString())).thenReturn(builder);
        when(request.headers()).thenReturn(headers);
        when(headers.names()).thenReturn(headerNames);
        when(headerNames.contains("Authorization")).thenReturn(false);
        PowerMockito.mockStatic(SessionLocalData.class);
        authInterceptor = new AuthInterceptor();
    }

    @Test
    public void shouldAttachAccessTokenIntoHTTPRequestHeaderWhenItCanFetchAccessTokenFromLocalStorage() throws Exception {
        when(SessionLocalData.fetchSessionInfoFromStorage()).thenReturn(sessionInfo);
        when(sessionInfo.getAccessToken()).thenReturn(HEADER_AUTH_VALUE);
        authInterceptor.intercept(chain);
        verify(builder).header(HEADER_AUTH_KEY, HEADER_AUTH_VALUE_PREFIX + HEADER_AUTH_VALUE);
    }

    @Test
    public void shouldAttachEmptyTokenIntoHTTPRequestHeaderWhenItCanNotFetchAccessTokenFromLocalStorage() throws Exception {
        when(SessionLocalData.fetchSessionInfoFromStorage()).thenReturn(null);
        authInterceptor.intercept(chain);
        verify(builder).header(HEADER_AUTH_KEY, "");
    }

    @Test
    public void shouldProceedRequest() throws Exception {
        when(SessionLocalData.fetchSessionInfoFromStorage()).thenReturn(null);
        authInterceptor.intercept(chain);
        verify(chain).proceed(request);
    }

    @Test
    public void shouldNotAttachTokenWhenTheOrigianlRequestHasAnAuthorizationFieldInHeader() throws Exception {
        when(headerNames.contains("Authorization")).thenReturn(true);
        authInterceptor.intercept(chain);
        verify(request, never()).newBuilder();
        verify(builder, never()).header(eq("Authorization"), anyString());
        verify(builder, never()).build();
        verify(chain).proceed(request);
    }
}