package com.assetowl.android.ui.component.spinner;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assetowl.android.R;

/**
 * Created by patrickyin on 30/5/17.
 */

public class LoadingSpinner extends LinearLayout {

    private TextView textView;

    public LoadingSpinner(Context context) {
        super(context);
        init(context);
    }

    public LoadingSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.component_loading_spinner, this);
        textView = (TextView) findViewById(R.id.progress_bar_text);
        setVisibility(GONE);
    }

    public void setText(@StringRes int resId) { setText(getContext().getString(resId)); }

    public void setText(String text) {
        textView.setText(text);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public String getText() { return textView.getText().toString(); }
}
