package com.assetowl.android;

import com.assetowl.android.di.component.AssetOwlApplicationComponent;
import com.assetowl.android.di.component.DaggerAssetOwlApplicationComponent;
import com.assetowl.android.di.factory.AssetOwlComponentFactory;
import com.assetowl.android.di.factory.DefaultAssetOwlComponentFactory;
import com.assetowl.android.di.module.AssetOwlApplicationModule;
import com.assetowl.android.di.module.AssetOwlIOModule;
import com.assetowl.android.mvp.AndroidApplication;
import com.assetowl.android.mvp.di.component.ApplicationComponent;
import com.assetowl.android.mvp.di.module.ApplicationModule;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.mvp.utils.Preconditions;

import io.intercom.android.sdk.Intercom;
import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by jamespott on 3/2/17.
 */

public class AssetOwlApplication extends AndroidApplication {

    private static AssetOwlApplication instance;
    private AssetOwlComponentFactory componentFactory;
    private static AssetOwlApplicationComponent appComponent;

    public static AssetOwlApplication getInstance() {
        return instance;
    }

    public static void setInstance(final AssetOwlApplication application) {
        instance = application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        Intercom.initialize(this, BuildConfig.INTERCOM_API_KEY, BuildConfig.INTERCOM_APP_ID);
    }

    @Override
    protected ApplicationComponent loadApplicationComponent() {
        Preconditions.checkNotNull(new AssetOwlApplicationModule(this));
        appComponent =  DaggerAssetOwlApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, new RxEventBus()))
                .assetOwlApplicationModule(new AssetOwlApplicationModule(this))
                .assetOwlIOModule(new AssetOwlIOModule(this))
                .build();
        componentFactory = new DefaultAssetOwlComponentFactory(appComponent);
        return appComponent;

    }

    public AssetOwlComponentFactory getComponentFactory() {
        return componentFactory;
    }

    public ApplicationComponent getComponent() {
        return appComponent;
    }

}
