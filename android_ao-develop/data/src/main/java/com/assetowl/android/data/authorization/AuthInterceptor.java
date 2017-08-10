package com.assetowl.android.data.authorization;

import com.assetowl.android.data.localstorage.SessionLocalData;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by patrickyin on 19/5/17.
 */

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(!request.headers().names().contains("Authorization")) {
            Request.Builder requestBuilder = request.newBuilder()
                    .header("Authorization", SessionLocalData.fetchSessionInfoFromStorage() != null ? "Bearer " + SessionLocalData.fetchSessionInfoFromStorage().getAccessToken() : "");
            request = requestBuilder.build();
        }
        return chain.proceed(request);
    }
}
