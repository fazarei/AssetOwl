package com.assetowl.android.di.component;

import com.assetowl.android.data.login.repository.LoginAnalyticsRepository;
import com.assetowl.android.data.login.repository.LogoutAnalyticsRepository;
import com.assetowl.android.di.module.AssetOwlApplicationModule;
import com.assetowl.android.di.module.AssetOwlIOModule;
import com.assetowl.android.mvp.di.component.ApplicationComponent;
import com.assetowl.android.mvp.di.module.ApplicationModule;
import com.assetowl.android.mvp.ui.BaseActivity;
import com.assetowl.domain.featureToggle.repository.FeatureToggleRepository;
import com.assetowl.domain.audits.templates.repository.TemplatesRepository;
import com.assetowl.domain.login.analytics.builder.LoginAnalyticsEventBuilder;
import com.assetowl.domain.login.analytics.builder.LogoutAnalyticsEventBuilder;
import com.assetowl.domain.login.repository.LoginRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jamespott on 3/2/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, AssetOwlApplicationModule.class, AssetOwlIOModule.class})
public interface AssetOwlApplicationComponent extends ApplicationComponent {

    LoginRepository getLoginRepository();
    LoginAnalyticsEventBuilder getLoginAnalyticsEventBuilder();
    LoginAnalyticsRepository getLoginAnalyticsRepository();
    LogoutAnalyticsEventBuilder getLogoutAnalyticsEventBuilder();
    LogoutAnalyticsRepository getLogoutAnalyticsRepository();
    FeatureToggleRepository getFeatureToggleRepository();
    TemplatesRepository getTemplatesRepository();

    @Override
    void inject(BaseActivity baseActivity);
}
