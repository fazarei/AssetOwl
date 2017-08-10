package com.assetowl.android.ui.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.assetowl.android.AssetOwlApplication;
import com.assetowl.android.R;
import com.assetowl.android.mvp.ui.PresenterActivity;
import com.assetowl.android.ui.audits.myaudits.MyAuditsFragment;
import com.assetowl.android.ui.audits.create.CreateAuditWorkflowActivity;
import com.assetowl.android.ui.login.LoginActivity;
import com.assetowl.android.utils.FragmentUtils;

import net.hockeyapp.android.UpdateManager;

import butterknife.Bind;

/**
 * Created by patrickyin on 9/3/17.
 */
public class DashboardActivity extends PresenterActivity<DashboardPresenter> implements DashboardView {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    FragmentManager fragmentManager = getSupportFragmentManager();

    Fragment myAuditFragment = new MyAuditsFragment();

    private TextView txtName;
    private TextView txtProfile;
    private View navHeader;

    @Override
    protected void initialiseInjector() {
        final AssetOwlApplication application = (AssetOwlApplication) getApplicationContext();
        application.getComponentFactory()
                .createDashboardComponent(getActivityModule())
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.checkForUpdate();
        presenter.initPushNotifications();
        presenter.loadMyAudits();

        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtProfile = (TextView) navHeader.findViewById(R.id.tv_profile);
        setUpNavigationView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregisterAppUpdateManager();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unregisterAppUpdateManager();
    }

    public void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(menuItem -> menuSelection(menuItem));

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public boolean menuSelection(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_item_dashboard:
                presenter.loadMyAudits();
                break;
            case R.id.menu_item_app_support:
                presenter.sendMessageByIntercom();
                drawer.closeDrawer(navigationView);
                return false;
            case R.id.menu_item_logout:
                presenter.logOut();
                break;
            default:
        }
        drawer.closeDrawer(navigationView);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_dashboard;
    }

    @Override
    public void navigateToLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public void initialiseView() {
        setBarTitle(R.string.my_audits);
        setUpNavigationView();
        navigationView.setItemIconTintList(null);
        navigationView.setCheckedItem(R.id.menu_item_dashboard);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    private void setBarTitle(int resId) {
        toolbar.setTitle(resId);
    }

    public void checkHockeyAppForUpdate() {
        UpdateManager.register(this);
    }

    @Override
    public void setBarTitleTemplatesList() {
        setBarTitle(R.string.audit_templates);
    }

    @Override
    public void replaceMainContentFragmentWithMyAudits() {
        FragmentUtils.replaceFragment(fragmentManager, R.id.main_content, myAuditFragment);
    }

    @Override
    public void setBarTitleMyAudits() {
        setBarTitle(R.string.my_audits);
    }

    @Override
    public void setUsernameInHeader(String username) {
        txtName.setText(username);
    }

    @Override
    public void setInitialiseName(String name) {
        txtProfile.setText(name.toUpperCase());
    }

    @Override
    public void navigateToTemplatePage() {
        Intent intent = new Intent(this, CreateAuditWorkflowActivity.class);
        this.startActivity(intent);
    }
}