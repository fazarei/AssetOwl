package com.assetowl.android.ui.component.actionbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.assetowl.android.R;

/**
 * Created by patrickyin on 1/3/17.
 */

public class SmallTitleActionBar extends RelativeLayout {

    private TextView titleTextView;

    public SmallTitleActionBar(Context context) {
        super(context);
        init();
    }

    public SmallTitleActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmallTitleActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SmallTitleActionBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.component_actionbar, this);
        titleTextView = (TextView) findViewById(R.id.action_bar_title_right);
    }

    public void setTitle(@StringRes int resId) {
        titleTextView.setText(resId);
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

}
