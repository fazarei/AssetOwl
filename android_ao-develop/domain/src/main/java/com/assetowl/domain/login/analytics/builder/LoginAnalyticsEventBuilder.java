package com.assetowl.domain.login.analytics.builder;

import com.assetowl.android.mvp.domain.event.AnalyticsEvent;
import com.assetowl.android.mvp.domain.event.AnalyticsEventBuilder;
import com.assetowl.domain.login.model.LoginAnalyticsEvent;

/**
 * Created by patrickyin on 7/4/17.
 */

public class LoginAnalyticsEventBuilder implements AnalyticsEventBuilder {
    private String value;

    @Override
    public AnalyticsEventBuilder add(String key, String value) {
        this.value = value;
        return this;
    }

    @Override
    public AnalyticsEvent build() {
        return new LoginAnalyticsEvent(value);
    }
}
