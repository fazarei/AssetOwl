package com.assetowl.domain.login.analytics.builder;

import com.assetowl.android.mvp.domain.event.AnalyticsEvent;
import com.assetowl.android.mvp.domain.event.AnalyticsEventBuilder;
import com.assetowl.domain.login.model.LoginAnalyticsEvent;
import com.assetowl.domain.login.model.LogoutAnalyticsEvent;

/**
 * Created by patrickyin on 7/4/17.
 */

public class LogoutAnalyticsEventBuilder implements AnalyticsEventBuilder {

    @Override
    public AnalyticsEventBuilder add(String key, String value) {
        return this;
    }

    @Override
    public AnalyticsEvent build() { return new LogoutAnalyticsEvent(); }
}
