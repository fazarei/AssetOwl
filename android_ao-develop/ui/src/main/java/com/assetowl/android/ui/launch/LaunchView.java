package com.assetowl.android.ui.launch;

import android.app.Activity;
import android.content.Context;

/**
 * Created by farzanehzarei on 1/3/17.
 */

public interface LaunchView {
    Context getContext();

    void launchLogin();

    void launchDashboard();
}
