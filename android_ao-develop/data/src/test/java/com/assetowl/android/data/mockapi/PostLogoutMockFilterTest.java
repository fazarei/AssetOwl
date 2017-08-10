package com.assetowl.android.data.mockapi;

import android.content.Context;

import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * Created by patrickyin on 24/4/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Gson.class})
public class PostLogoutMockFilterTest {
    @Mock
    Context context;
    @Mock
    Gson gson;

    @Test
    public void shouldReturnNullString() throws Exception {
        PostLogoutMockFilter postLogoutMockFilter = new PostLogoutMockFilter(context, gson);
        assertEquals("null", postLogoutMockFilter.getResponseString());
    }

}