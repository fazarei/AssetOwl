package com.assetowl.android.data.featureToggle.repository;

import com.assetowl.android.data.featureToggle.api.AuthFeatureToggle;
import com.assetowl.android.data.featureToggle.model.FeatureToggle;
import com.assetowl.android.data.localstorage.SessionLocalData;
import com.assetowl.android.data.localstorage.utils.LocalStorageHelper;
import com.assetowl.domain.featureToggle.model.FeatureToggleInfo;
import com.assetowl.domain.featureToggle.repository.FeatureToggleRepository;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmModel;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by farzanehzarei on 17/5/17.
 */

public class FeatureToggleDataRepository implements FeatureToggleRepository {

    private final AuthFeatureToggle authFeatureToggle;

    @Inject
    FeatureToggleDataRepository(Retrofit retrofit) {
        authFeatureToggle = retrofit.create(AuthFeatureToggle.class);
    }

    @Override
    public Observable<List<? extends FeatureToggleInfo>> featureToggle() {
        return authFeatureToggle.featureToggle()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<List<FeatureToggle>, List<? extends FeatureToggleInfo>>() {
                    @Override
                    public List<? extends FeatureToggleInfo> call(List<FeatureToggle> featureToggles) {

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.delete(FeatureToggle.class);
                        realm.createAllFromJson(FeatureToggle.class, new Gson().toJson(featureToggles));
                        realm.commitTransaction();

                        return featureToggles;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, List<? extends FeatureToggleInfo>>() {
                    @Override
                    public List<? extends FeatureToggleInfo> call(Throwable throwable) {
                        return LocalStorageHelper.fetchAll(FeatureToggle.class);
                    }
                });
    }
}
