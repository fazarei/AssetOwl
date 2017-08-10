package com.assetowl.android.data.login.api;

import com.assetowl.android.data.login.model.TermsAndCondition;
import com.assetowl.android.data.login.model.UserSession;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by patrickyin on 14/2/17.
 */

public interface Auth {
    @POST("/cm/login")
    Observable<UserSession> login(@Body RequestBody requestBody);

    @GET("cm/tos/latest")
    Observable<TermsAndCondition> termAndConditionContent(@Header("Authorization") String accessToken);

    @POST("cm/tos/version/{version}/accept")
    Observable<UserSession> termAndConditionAccept(@Header("Authorization") String accessToken, @Path("version") int version);

    @POST("/cm/logout")
    Observable<Object> logout(@Body RequestBody requestBody);
}
