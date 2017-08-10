package com.assetowl.android.di.factory;

import com.assetowl.android.di.component.AssetOwlApplicationComponent;
import com.assetowl.android.mvp.di.module.ActivityModule;
import com.assetowl.android.mvp.di.module.FragmentModule;
import com.assetowl.android.ui.audits.create.di.component.DaggerCreateAuditWorkflowComponent;
import com.assetowl.android.ui.audits.create.properties.di.component.DaggerSelectPropertiesComponent;
import com.assetowl.android.ui.audits.create.templates.di.component.SelectAuditTemplatesComponent;
import com.assetowl.android.ui.audits.create.di.component.CreateAuditWorkflowComponent;
import com.assetowl.android.ui.audits.create.properties.di.component.SelectPropertiesComponent;
import com.assetowl.mvp.utils.Preconditions;
import com.assetowl.android.ui.audits.myaudits.di.component.DaggerMyAuditsComponent;
import com.assetowl.android.ui.audits.myaudits.di.component.MyAuditsComponent;
import com.assetowl.android.ui.audits.create.templates.di.component.DaggerSelectAuditTemplatesComponent;
import com.assetowl.android.ui.dashboard.di.component.DaggerDashboardComponent;
import com.assetowl.android.ui.dashboard.di.component.DashboardComponent;
import com.assetowl.android.ui.launch.di.component.DaggerLaunchComponent;
import com.assetowl.android.ui.launch.di.component.LaunchComponent;
import com.assetowl.android.ui.login.di.component.DaggerLoginComponent;
import com.assetowl.android.ui.login.di.component.LoginComponent;
import com.assetowl.android.ui.login.di.module.LoginModule;

/**
 * Create module components using Dagger2 generated factories
 *
 * Created by jamespott on 7/2/17.
 */

public class DefaultAssetOwlComponentFactory implements AssetOwlComponentFactory {

    private final AssetOwlApplicationComponent applicationComponent;

    public DefaultAssetOwlComponentFactory(AssetOwlApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    @Override
    public LoginComponent createLoginComponent(ActivityModule activityModule) {
        Preconditions.checkNotNull(activityModule);
        return DaggerLoginComponent.builder()
                    .assetOwlApplicationComponent(applicationComponent)
                    .loginModule(new LoginModule())
                    .activityModule(activityModule)
                    .build();
    }

    @Override
    public DashboardComponent createDashboardComponent(ActivityModule activityModule) {
        Preconditions.checkNotNull(activityModule);
        return DaggerDashboardComponent.builder()
                    .assetOwlApplicationComponent(applicationComponent)
                    .activityModule(activityModule)
                    .build();
    }

    @Override
    public LaunchComponent createLaunchComponent(ActivityModule activityModule) {
        Preconditions.checkNotNull(activityModule);
        return DaggerLaunchComponent.builder()
                .assetOwlApplicationComponent(applicationComponent)
                .activityModule(activityModule)
                .build();
    }

    @Override
    public SelectAuditTemplatesComponent createSelectAuditTemplatesComponent(FragmentModule fragmentModule) {
        return DaggerSelectAuditTemplatesComponent.builder()
                .assetOwlApplicationComponent(applicationComponent)
                .fragmentModule(Preconditions.checkNotNull(fragmentModule))
                .build();
    }

    @Override
    public MyAuditsComponent createMyAuditsComponent(FragmentModule fragmentModule) {
        return DaggerMyAuditsComponent.builder()
                .assetOwlApplicationComponent(applicationComponent)
                .fragmentModule(Preconditions.checkNotNull(fragmentModule))
                .build();
    }

    @Override
    public CreateAuditWorkflowComponent createAuditWorkflowComponent(ActivityModule activityModule) {
        Preconditions.checkNotNull(activityModule);
        return DaggerCreateAuditWorkflowComponent.builder()
                .assetOwlApplicationComponent(applicationComponent)
                .activityModule(activityModule)
                .build();
    }

    @Override
    public SelectPropertiesComponent createSelectPropertiesComponent(FragmentModule fragmentModule) {
        return DaggerSelectPropertiesComponent.builder()
                .assetOwlApplicationComponent(applicationComponent)
                .fragmentModule(Preconditions.checkNotNull(fragmentModule))
                .build();
    }
}
