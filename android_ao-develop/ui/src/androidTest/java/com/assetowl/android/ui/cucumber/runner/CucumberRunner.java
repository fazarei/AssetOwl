package com.assetowl.android.ui.cucumber.runner;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import com.assetowl.android.BuildConfig;

import cucumber.api.android.CucumberInstrumentationCore;

public class CucumberRunner extends AndroidJUnitRunner {
    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);
    private static final String CUCUMBER_TAGS_KEY = "tags";
    private static final String CUCUMBER_FEATURES_KEY = "name";
    @Override
    public void onCreate(Bundle bundle) {
        String tags = BuildConfig.TEST_TAGS;
        if (!tags.isEmpty()) {
            bundle.putString(CUCUMBER_TAGS_KEY, tags.replaceAll("\\s", ""));
        }

        String feature = BuildConfig.TEST_FEATURES;
        if (!feature.isEmpty()) {
            feature = feature.replaceAll(" ", "\\\\s");
            bundle.putString(CUCUMBER_FEATURES_KEY, feature);
        }

        super.onCreate(bundle);
        instrumentationCore.create(bundle);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }
}
