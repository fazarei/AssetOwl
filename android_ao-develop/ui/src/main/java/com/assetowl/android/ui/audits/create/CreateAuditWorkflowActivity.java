package com.assetowl.android.ui.audits.create;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.ImageView;

import com.assetowl.android.AssetOwlApplication;
import com.assetowl.android.R;
import com.assetowl.android.mvp.ui.PresenterActivity;
import com.assetowl.android.ui.audits.create.CreateAuditWorkflowPagerAdapter;
import com.assetowl.android.ui.audits.create.CreateAuditWorkflowPresenter;
import com.assetowl.android.ui.audits.create.CreateAuditWorkflowView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by farzanehzarei on 6/6/17.
 */

public class CreateAuditWorkflowActivity extends PresenterActivity<CreateAuditWorkflowPresenter> implements CreateAuditWorkflowView {

    @Bind(R.id.img_close)
    ImageView imgClose;

    @Bind(R.id.btn_next)
    Button btn_next;

    @Bind(R.id.create_audit_workflow_viewpager)
    ViewPager viewPager;

    CreateAuditWorkflowPagerAdapter createAuditWorkflowPagerAdapter;

    @Override
    protected void initialiseInjector() {
        final AssetOwlApplication application = (AssetOwlApplication) getApplicationContext();
        application.getComponentFactory()
                .createAuditWorkflowComponent(getActivityModule())
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorStatusBarBackground));
        }
        presenter.initialiseOnCreate();
    }

    @OnClick(R.id.img_close)
    void closeTemplateList() {
        this.finish();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_create_audit_workflow;
    }

    @Override
    public void initialiseView() {
    }

    @Override
    public void enableNextButton() {
        btn_next.setEnabled(true);
        btn_next.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_droid_chevron_right, 0);
    }

    @Override
    public void deActiveNextButton() {
        btn_next.setEnabled(false);
        btn_next.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_droid_chevron_right_disabled, 0);
    }

    @Override
    public void initialiseViewPager() {
        createAuditWorkflowPagerAdapter = new CreateAuditWorkflowPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(createAuditWorkflowPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @OnClick(R.id.btn_next)
    void nextStep() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }
}
