package com.assetowl.android.data.utils;

import com.assetowl.android.data.TestConstants;
import com.assetowl.android.data.login.model.UserDataCredentials;
import com.assetowl.domain.login.model.UserCredentials;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import okhttp3.RequestBody;
import okio.Buffer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by patrickyin on 15/2/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class HTTPRequestBodyHelperUnitTest {
    @Mock
    JSONObject jsonObject;

    @Test
    public void fromGson() throws Exception {
        UserCredentials userCredentials = new UserDataCredentials(TestConstants.TEST_ACCOUNT_EMAIL, TestConstants.TEST_ACCOUNT_PASSWORD);
        RequestBody request = HTTPRequestBodyHelper.fromGson(userCredentials);
        assertEquals(request.contentType().toString(), "application/json; charset=utf-8");

        Buffer buffer = new Buffer();
        request.writeTo(buffer);
        assertEquals(buffer.readUtf8(), "{\"email\":\"" + TestConstants.TEST_ACCOUNT_EMAIL + "\",\"password\":\"" + TestConstants.TEST_ACCOUNT_PASSWORD + "\"}");
    }

    @Test
    public void fromJSONObject() throws Exception {
        when(jsonObject.toString()).thenReturn("{\"refreshToken\": \"testtest\"}");
        RequestBody requestBody = HTTPRequestBodyHelper.fromJSONObject(jsonObject);
        assertEquals("application/json; charset=utf-8", requestBody.contentType().toString());

        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        assertEquals("{\"refreshToken\": \"testtest\"}", buffer.readUtf8());
    }
}