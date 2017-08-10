package com.assetowl.domain.login.model;

import com.assetowl.android.mvp.domain.event.AnalyticsEvent;
import com.assetowl.mvp.utils.Preconditions;

/**
 * Created by patrickyin on 12/4/17.
 */

public class LoginAnalyticsEvent implements AnalyticsEvent {
    private final String email;

    public LoginAnalyticsEvent(String email) {
        this.email = Preconditions.checkNotNull(email);
    }

    public Object getDataFor(String key) {
        return email;
    }
}
