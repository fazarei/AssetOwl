package com.assetowl.android.ui.dashboard;

import android.content.Context;

/**
 * Created by patrickyin on 9/3/17.
 */

public interface DashboardView {
    void initialiseView();
    void navigateToLoginPage();
    Context getContext();
    void checkHockeyAppForUpdate();
    void setBarTitleTemplatesList();
    void replaceMainContentFragmentWithMyAudits();
    void setBarTitleMyAudits();
    void setUsernameInHeader(String username);
    void setInitialiseName(String name);
    void navigateToTemplatePage();
}
