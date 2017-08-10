package com.assetowl.android.di.module;

import android.app.Application;

import com.assetowl.android.AssetOwlApplication;
import com.assetowl.android.data.featureToggle.repository.FeatureToggleDataRepository;
import com.assetowl.android.data.login.repository.LoginAnalyticsRepository;
import com.assetowl.android.data.login.repository.LoginDataRepository;
import com.assetowl.android.data.login.repository.LogoutAnalyticsRepository;
import com.assetowl.domain.featureToggle.repository.FeatureToggleRepository;
import com.assetowl.android.data.audits.templates.repository.TemplatesDataRepository;
import com.assetowl.android.data.login.repository.LoginAnalyticsRepository;
import com.assetowl.android.data.login.repository.LoginDataRepository;
import com.assetowl.android.data.login.repository.LogoutAnalyticsRepository;
import com.assetowl.android.mvp.domain.event.AnalyticsEventBuilder;
import com.assetowl.android.mvp.domain.repository.AnalyticsRepository;
import com.assetowl.domain.audits.templates.repository.TemplatesRepository;
import com.assetowl.domain.login.analytics.builder.LoginAnalyticsEventBuilder;
import com.assetowl.domain.login.analytics.builder.LogoutAnalyticsEventBuilder;
import com.assetowl.domain.login.repository.LoginRepository;

import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by jamespott on 3/2/17.
 */
@Module
public class AssetOwlApplicationModule {

    private static final int FIXED_THREAD_POOL_SIZE = 4;
    private final AssetOwlApplication application;
    private final Scheduler boundedIoScheduler;

    public AssetOwlApplicationModule() {
        application = null;
        boundedIoScheduler = null;
    }

    public AssetOwlApplicationModule(AssetOwlApplication app) {
        this.application = app;
        this.boundedIoScheduler = Schedulers.from(Executors.newFixedThreadPool(FIXED_THREAD_POOL_SIZE));
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    @Named("bufferScheduler")
    Scheduler provideBufferScheduler() {
        return boundedIoScheduler;
    }

    @Provides
    @Singleton
    LoginRepository provideLoginRepository(LoginDataRepository loginRepository) {
        return loginRepository;
    }

    @Provides
    @Singleton
    TemplatesRepository provideTemplatesRepository(TemplatesDataRepository templatesDataRepository) {
        return templatesDataRepository;
    }

    @Provides
    @Singleton
    LoginAnalyticsRepository provideLoginAnalyticsRepository() {
        return new LoginAnalyticsRepository();
    }

    @Provides
    @Singleton
    LoginAnalyticsEventBuilder provideLoginAnalyticsEventBuilder() {
        return new LoginAnalyticsEventBuilder();
    }

    @Provides
    @Singleton
    LogoutAnalyticsRepository provideLogoutAnalyticsRepository() {
        return new LogoutAnalyticsRepository();
    }

    @Provides
    @Singleton
    LogoutAnalyticsEventBuilder provideLogoutAnalyticsEventBuilder() {
        return new LogoutAnalyticsEventBuilder();
    }

    @Provides
    @Singleton
    FeatureToggleRepository provideFeatureToggleRepository(FeatureToggleDataRepository featureToggleDataRepository) {
        return featureToggleDataRepository;
    }

}
