package com.assetowl.android.data.featureToggle.api;

import com.assetowl.android.data.featureToggle.model.FeatureToggle;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by farzanehzarei on 17/5/17.
 */

public interface AuthFeatureToggle {
    @GET("/coin/coins")
    Observable<List<FeatureToggle>> featureToggle();
}
