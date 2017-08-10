package com.assetowl.android.ui.component.actionbar;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by patrickyin on 1/3/17.
 */

public class ActionBarManager {
    private Activity activity;
    private SmallTitleActionBar smallTitleActionBar;
    public ActionBarManager(Activity activity) {
        this.activity = activity;
    }


    public void initAssetOwlActionBar(@StringRes int titleStringRes) {
        initAssetOwlActionBar(activity.getResources().getString(titleStringRes));
    }

    public void initAssetOwlActionBar(String title) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        if (actionBar != null) {
            smallTitleActionBar = new SmallTitleActionBar(activity);
            smallTitleActionBar.setTitle(title);
            actionBar.setCustomView(smallTitleActionBar);
            actionBar.setDisplayShowCustomEnabled(true);
            Toolbar parent = (Toolbar) actionBar.getCustomView().getParent();
            parent.setContentInsetsAbsolute(0, 0);
        }
    }

    public void setTitle(@StringRes int resId) {
        smallTitleActionBar.setTitle(resId);
    }

    public void setTitle(String title) {
        smallTitleActionBar.setTitle(title);
    }

}
