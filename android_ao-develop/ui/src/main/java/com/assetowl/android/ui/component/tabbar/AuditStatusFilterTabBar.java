package com.assetowl.android.ui.component.tabbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IntRange;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assetowl.android.R;
import com.assetowl.android.utils.DimensionUnitConverter;

/**
 * Created by patrickyin on 24/5/17.
 */

public class AuditStatusFilterTabBar extends android.support.design.widget.TabLayout {
    private Tab tabNewAudits, tabInProgress, tabSubmitted;

    public enum AuditStatusFilterOptions{
        NEW_AUDITS,
        IN_PROGRESS,
        SUBMITTED
    }

    public AuditStatusFilterTabBar(Context context) {
        super(context);
        initializeUI();
    }

    public AuditStatusFilterTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeUI();
    }

    public AuditStatusFilterTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeUI();
    }

    private void initializeUI() {
        tabNewAudits = this.newTab().setCustomView(R.layout.component_tabitem_audit_status_filter);
        tabInProgress = this.newTab().setCustomView(R.layout.component_tabitem_audit_status_filter);
        tabSubmitted = this.newTab().setCustomView(R.layout.component_tabitem_audit_status_filter);
        tabNewAudits.setTag(AuditStatusFilterOptions.NEW_AUDITS);
        tabInProgress.setTag(AuditStatusFilterOptions.IN_PROGRESS);
        tabSubmitted.setTag(AuditStatusFilterOptions.SUBMITTED);
        this.addTab(tabNewAudits);
        this.addTab(tabInProgress);
        this.addTab(tabSubmitted);
        addDividers();
        initializeTexts();
        initializeListener();
        setCountTextBold(tabNewAudits);
        tabNewAudits.select();
    }

    private void addDividers() {
        LinearLayout linearLayout = (LinearLayout)this.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(getResources().getColor(R.color.colorTabDividerColor));
        drawable.setSize(DimensionUnitConverter.dpToPx(1), 1);
        linearLayout.setDividerPadding(DimensionUnitConverter.dpToPx(8));
        linearLayout.setDividerDrawable(drawable);
    }

    private void initializeTexts() {
        setNewAuditsCount(0);
        setInProgressAuditsCount(0);
        setSubmittedAuditsCount(0);
        setItemText(tabNewAudits, R.string.new_audits);
        setItemText(tabInProgress, R.string.in_progress);
        setItemText(tabSubmitted, R.string.submitted);
    }

    private void setItemText(Tab tab, @StringRes int resId) {
        if(tab.getCustomView() != null) {
            TextView tabText = (TextView) tab.getCustomView().findViewById(R.id.tabitem_text_title);
            if(tabText != null) tabText.setText(resId);
        }
    }

    private void setAuditsCount(Tab tab, @IntRange(from = 0) int count) {
        if(tab.getCustomView() != null) {
            TextView auditsCount = (TextView) tab.getCustomView().findViewById(R.id.tabitem_text_count);
            if(auditsCount != null) auditsCount.setText(String.valueOf(count));
        }
    }

    public void setSubmittedAuditsCount(int count) {
        setAuditsCount(tabSubmitted, count);
    }

    public void setInProgressAuditsCount(int count) {
        setAuditsCount(tabInProgress, count);
    }

    public void setNewAuditsCount(int count) {
        setAuditsCount(tabNewAudits, count);
    }

    private void initializeListener() {
        this.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                setCountTextBold(tab);
            }

            @Override
            public void onTabUnselected(Tab tab) {
                clearCountTextBold(tab);
            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });
    }

    private void clearCountTextBold(Tab tab) {
        TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tabitem_text_count);
        if(textView != null) textView.setTypeface(null, Typeface.NORMAL);
    }

    private void setCountTextBold(Tab tab) {
        TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tabitem_text_count);
        if(textView != null) textView.setTypeface(null, Typeface.BOLD);
    }
}
