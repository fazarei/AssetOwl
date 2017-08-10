package com.assetowl.android.data.mockapi.lib;

import android.content.Context;
import android.util.Log;

import com.assetowl.android.data.mockapi.PostLoginMockFilter;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by patrickyin on 27/3/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Gson.class, Request.class, HttpUrl.class, Log.class, Class.class, Response.class})
public class FakeInterceptorTest {
    @Mock
    Gson gson;
    @Mock
    Context context;
    @Mock
    Interceptor.Chain chain;
    @Mock
    Request request;
    @Mock
    HttpUrl httpUrl;
    @Mock
    URI uri;
    @Mock
    RequestBody requestBody;
    @Mock
    PostLoginMockFilter postLoginMockFilter;
    @Mock
    Response response;

    private FakeInterceptor fakeInterceptor;

    @Before
    public void setUp() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        mockStatic(Log.class);


        List<String> urlList = new ArrayList<String>();
        urlList.add("login");

        fakeInterceptor = new FakeInterceptor(context, gson);
        fakeInterceptor.setMockFilter(postLoginMockFilter);
        when(chain.request())
                .thenReturn(request);
        when(chain.request().body())
                .thenReturn(requestBody);
        when(chain.request().method())
                .thenReturn("post");
        when(chain.request().url())
                .thenReturn(httpUrl);
        when(chain.request().url().uri())
                .thenReturn(uri);
        when(chain.request().url().pathSegments())
                .thenReturn(urlList);
        when(postLoginMockFilter.getResponseString())
                .thenReturn("null");
        when(postLoginMockFilter.getResponseStatusCode())
                .thenReturn(200);
        when(postLoginMockFilter.buildResponse())
                .thenReturn(response);
        when(response.code())
                .thenReturn(200);
    }

    @Test
    public void shouldHaveSetContentTypeMethod() throws Exception{
        fakeInterceptor.setContentType("application/json");
    }

    @Test
    public void shouldBuildAHTTP200MockResponse() throws Exception {
        Response response = fakeInterceptor.intercept(chain);
        verify(chain, atLeastOnce()).request();
        verify(postLoginMockFilter).buildResponse();
        assertEquals(200, response.code());
    }
    @Test
    public void shouldBuildACustomMockResponse() throws Exception {
        when(postLoginMockFilter.getCustomResponse()).thenReturn(this.response);
        Response response = fakeInterceptor.intercept(chain);
        verify(chain, atLeastOnce()).request();
        verify(postLoginMockFilter).buildResponse();
        assertEquals(this.response, response);
    }

}