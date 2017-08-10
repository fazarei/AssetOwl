package com.assetowl.android.data.mockapi.lib;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import java.util.Date;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by patrickyin on 27/3/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Gson.class, Request.class, Thread.class, Log.class})
public class MockFilterTest {
    @Mock
    Context context;
    @Mock
    Gson gson;
    @Mock
    Request request;
    @Mock
    RequestBody requestBody;

    private MockTestFilter testMockFilter;

    @Before
    public void setUp() {
        mockStatic(Log.class);
        mockStatic(Thread.class);
        when(request.body()).thenReturn(requestBody);
        when(request.body())
                .thenReturn(requestBody);
        testMockFilter = new MockTestFilter(context, gson);
        testMockFilter.setRequest(request);
    }

    @Test
    public void shouldHaveSetContentTypeMethod() throws Exception{
        testMockFilter.setContentType("application/json");
    }

    @Test
    public void shouldSetAndReturnResponseStatusCode() throws Exception {
        testMockFilter.setResponseStatusCode(200);
        assertEquals(200, testMockFilter.getResponseStatusCode());
        testMockFilter.setResponseStatusCode(400);
        assertEquals(400, testMockFilter.getResponseStatusCode());
    }

    @Test
    public void shouldGenerateTimeoutResponse() throws Exception {
        long startTime = new Date().getTime();

        Response response = testMockFilter.generateTimeoutResponse();
        assertTrue(new Date().getTime() >= startTime + 5_000);
        assertEquals(504, response.code());
    }

    @Test
    public void shouldBuildResponse() throws Exception {
        Response response = testMockFilter.buildResponse();
        assertNotNull(response);
    }

    class MockTestFilter extends MockFilter {

        MockTestFilter(Context context, Gson gson) {
            super(context, gson);
        }

        @Override
        public String getResponseString() {
            return "null";
        }

        @Override
        public Response getCustomResponse() {
            return null;
        }
    }

}