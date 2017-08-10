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

public class LogoutAnalyticsRepository implements AnalyticsRepository {
    @Override
    public void push(AnalyticsEvent analyticsEvent) {
        logToIntercomEvent(analyticsEvent);
    }

    private void logToIntercomEvent(AnalyticsEvent analyticsEvent) {
        Intercom.client().logEvent(Constants.EVENT_ANALYTICS_NAME_DID_LOGOUT);
    }
}
