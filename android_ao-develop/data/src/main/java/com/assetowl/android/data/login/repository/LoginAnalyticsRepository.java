package com.assetowl.android.data.login.repository;

import com.assetowl.android.data.Constants;
import com.assetowl.android.mvp.domain.event.AnalyticsEvent;
import com.assetowl.android.mvp.domain.repository.AnalyticsRepository;

import java.util.HashMap;
import java.util.Map;

import io.intercom.android.sdk.Intercom;

/**
 * Created by jamespott on 6/4/17.
 */

public class LoginAnalyticsRepository implements AnalyticsRepository {
    @Override
    public void push(AnalyticsEvent analyticsEvent) {
        logToIntercomEvent(analyticsEvent);
    }

    private void logToIntercomEvent(AnalyticsEvent analyticsEvent) {
        Map<String, Object> eventMeta = new HashMap<>();
        eventMeta.put(Constants.EVENT_ANALYTICS_DATA_KEY_EMAIL, analyticsEvent.getDataFor("email"));
        Intercom.client().logEvent(Constants.EVENT_ANALYTICS_NAME_DID_LOGIN, eventMeta);
    }
}
