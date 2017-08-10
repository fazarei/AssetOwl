package com.assetowl.android.di.factory;

import com.assetowl.android.mvp.di.module.ActivityModule;
import com.assetowl.android.mvp.di.module.FragmentModule;
import com.assetowl.android.ui.audits.myaudits.di.component.MyAuditsComponent;
import com.assetowl.android.ui.audits.create.di.component.CreateAuditWorkflowComponent;
import com.assetowl.android.ui.audits.create.templates.di.component.SelectAuditTemplatesComponent;
import com.assetowl.android.ui.audits.create.properties.di.component.SelectPropertiesComponent;
import com.assetowl.android.ui.dashboard.di.component.DashboardComponent;
import com.assetowl.android.ui.login.di.component.LoginComponent;
import com.assetowl.android.ui.launch.di.component.LaunchComponent;

/**
 * Created by jamespott on 7/2/17.
 */

public interface AssetOwlComponentFactory {
    LaunchComponent createLaunchComponent(ActivityModule activityModule);
    LoginComponent createLoginComponent(ActivityModule activityModule);
    DashboardComponent createDashboardComponent(ActivityModule activityModule);
    MyAuditsComponent createMyAuditsComponent(FragmentModule fragmentModule);
    CreateAuditWorkflowComponent createAuditWorkflowComponent(ActivityModule activityModule);
    SelectAuditTemplatesComponent createSelectAuditTemplatesComponent(FragmentModule fragmentModule);
    SelectPropertiesComponent createSelectPropertiesComponent(FragmentModule fragmentModule);
}
