package com.assetowl.domain.login.repository;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jamespott on 22/3/17.
 */

public class LoggedInDescriptor {

    @StringDef({LoggedInStatus.YES, LoggedInStatus.NO})

    @Retention(RetentionPolicy.SOURCE)
    public @interface LoggedInDef {
    }

    @LoggedInDef
    public final String isLoggedIn;

    public LoggedInDescriptor(@LoggedInDef String isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
