package com.assetowl.android.ui.dashboard;

import com.assetowl.android.BuildConfig;
import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.ui.dashboard.observer.MenuHeaderInitialNameObserver;
import com.assetowl.domain.login.usecase.InitialNameUseCase;
import com.assetowl.mvp.presenter.BasePresenter;
import com.assetowl.android.ui.dashboard.observer.LogoutObserver;
import com.assetowl.android.ui.dashboard.observer.FeatureToggleObserver;
import com.assetowl.android.ui.dashboard.observer.MenuHeaderUserNameObserver;
import com.assetowl.domain.featureToggle.usecase.FeatureToggleUseCase;
import com.assetowl.domain.login.usecase.LastLoggedInUsernameUseCase;
import com.assetowl.domain.login.usecase.LogoutUseCase;

import net.hockeyapp.android.UpdateManager;

import javax.inject.Inject;
import javax.inject.Named;

import io.intercom.android.sdk.Intercom;

/**
 * Created by patrickyin on 9/3/17.
 */

@PerActivity
public class DashboardPresenter extends BasePresenter<DashboardView> {


    private LogoutUseCase logoutUseCase;
    private LogoutObserver logoutObserver;
    private FeatureToggleUseCase featureToggleUseCase;
    private FeatureToggleObserver featureToggleObserver;
    private LastLoggedInUsernameUseCase lastLoggedInUsernameUseCase;
    private InitialNameUseCase initialiseNameUseCase;
    private MenuHeaderUserNameObserver menuHeaderUserNameObserver;
    private MenuHeaderInitialNameObserver menuHeaderInitialNameObserver;

    @Inject
    public DashboardPresenter(@Named("logout") UseCase logoutUseCase, LogoutObserver logoutObserver,
                              @Named("featureList") UseCase featureToggleUseCase, FeatureToggleObserver featureToggleObserver,
                              @Named("lastLoggedInUsername") UseCase lastLoggedInUsernameUseCase, MenuHeaderUserNameObserver menuHeaderUserNameObserver,
                              @Named("initialName") UseCase initialiseNameUseCase, MenuHeaderInitialNameObserver menuHeaderInitialNameObserver) {

        this.logoutUseCase = (LogoutUseCase) logoutUseCase;
        this.logoutObserver = logoutObserver;
        this.logoutObserver.setDashboardPresenter(this);

        this.featureToggleUseCase = (FeatureToggleUseCase) featureToggleUseCase;
        this.featureToggleObserver = featureToggleObserver;
        this.featureToggleObserver.setDashboardPresenter(this);

        this.lastLoggedInUsernameUseCase = (LastLoggedInUsernameUseCase) lastLoggedInUsernameUseCase;
        this.menuHeaderUserNameObserver = menuHeaderUserNameObserver;
        this.menuHeaderUserNameObserver.setDashboardPresenter(this);

        this.initialiseNameUseCase = (InitialNameUseCase) initialiseNameUseCase;
        this.menuHeaderInitialNameObserver = menuHeaderInitialNameObserver;
        this.menuHeaderInitialNameObserver.setDashboardPresenter(this);
    }

    @Override
    public void start() {
        getView().initialiseView();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void showError(String message) {

    }

    public void logOut() {
        logoutUseCase.execute(logoutObserver);
    }

    public void logoutSuccess() {
        getView().navigateToLoginPage();
    }

    public void sendMessageByIntercom() {
        Intercom.client().displayMessenger();
    }

    public void checkForUpdate() {
        if (!BuildConfig.FLAVOR.equals("prod")) {
            getView().checkHockeyAppForUpdate();
        }
    }

    public void unregisterAppUpdateManager() {
        if (!BuildConfig.FLAVOR.equals("prod")) {
            UpdateManager.unregister();
        }
    }

    public void initPushNotifications() {
        Intercom.client().handlePushMessage();
    }

    public void loadMyAudits() {

        lastLoggedInUsernameUseCase.execute(menuHeaderUserNameObserver);
        initialiseNameUseCase.execute(menuHeaderInitialNameObserver);
        getView().setBarTitleMyAudits();
        getView().replaceMainContentFragmentWithMyAudits();
    }

    public void getFeatureToggle() {
        featureToggleUseCase.execute(featureToggleObserver);
    }

    public void setUsernameInHeader(String username) {
        getView().setUsernameInHeader(username);
    }

    public void setInitialiseName(String name) {
        getView().setInitialiseName(name);
    }
}
