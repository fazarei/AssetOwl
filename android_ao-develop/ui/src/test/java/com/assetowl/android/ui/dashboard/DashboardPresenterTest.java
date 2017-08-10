package com.assetowl.android.ui.dashboard;

import com.assetowl.android.BuildConfig;
import com.assetowl.android.ui.dashboard.observer.MenuHeaderInitialNameObserver;
import com.assetowl.domain.login.usecase.InitialNameUseCase;
import com.assetowl.mvp.presenter.BasePresenter;
import com.assetowl.android.ui.dashboard.observer.FeatureToggleObserver;
import com.assetowl.android.ui.dashboard.observer.LogoutObserver;
import com.assetowl.android.ui.dashboard.observer.MenuHeaderUserNameObserver;
import com.assetowl.domain.featureToggle.usecase.FeatureToggleUseCase;
import com.assetowl.domain.login.usecase.LastLoggedInUsernameUseCase;
import com.assetowl.domain.login.usecase.LogoutUseCase;
import com.assetowl.domain.session.model.SessionInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import io.intercom.android.sdk.Intercom;

import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by farzanehzarei on 17/3/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Intercom.class)
public class DashboardPresenterTest {

    private DashboardPresenter dashboardPresenter;

    @Mock
    DashboardView dashboardView;
    @Mock
    private SessionInfo sessionInfo;

    @Mock
    BasePresenter<DashboardView> basePresenter;

    @Mock
    private LogoutUseCase logoutUseCase;

    @Mock
    private LogoutObserver logoutObserver;

    @Mock
    private Intercom intercom;

    @Mock
    FeatureToggleUseCase featureToggleUseCase;
    @Mock
    FeatureToggleObserver featureToggleObserver;
    @Mock
    MenuHeaderUserNameObserver menuHeaderUserNameObserver;
    @Mock
    LastLoggedInUsernameUseCase lastLoggedInUsernameUseCase;
    @Mock
    MenuHeaderInitialNameObserver menuHeaderInitialNameObserver;
    @Mock
    InitialNameUseCase initialiseNameUseCase;

    @Before
    public void setUp() throws Exception{
        PowerMockito.mockStatic(Intercom.class);
        dashboardPresenter = new DashboardPresenter(logoutUseCase,logoutObserver,featureToggleUseCase,
                featureToggleObserver,lastLoggedInUsernameUseCase,menuHeaderUserNameObserver,initialiseNameUseCase,menuHeaderInitialNameObserver);
    }

    @Test
    public void initialiseStart() throws Exception {
        dashboardPresenter.attachView(dashboardView);
        dashboardPresenter.start();
        Mockito.verify(dashboardPresenter.getView()).initialiseView();
    }

    @Test
    public void logoutTest() throws Exception {
        dashboardPresenter.attachView(dashboardView);
        dashboardPresenter.logOut();
        dashboardPresenter.logoutSuccess();
        Mockito.verify(dashboardPresenter.getView()).navigateToLoginPage();
    }

    @Test
    public void checkForUpdate() throws Exception {
        if (!BuildConfig.FLAVOR.equals("prod")) {
            dashboardPresenter.attachView(dashboardView);
            dashboardPresenter.checkForUpdate();
            Mockito.verify(dashboardPresenter.getView()).checkHockeyAppForUpdate();
        }
    }

    @Test
    public void initPushNotifications() throws Exception {
        when(Intercom.client()).thenReturn(intercom);
        dashboardPresenter.attachView(dashboardView);
        dashboardPresenter.initPushNotifications();
        Mockito.verify(Intercom.client()).handlePushMessage();
    }

    @Test
    public void shouldExecuteFeatureToggle() throws Exception {
        dashboardPresenter.attachView(dashboardView);
        dashboardPresenter.getFeatureToggle();
        Mockito.verify(featureToggleUseCase).execute(featureToggleObserver);
    }

    @Test
    public void setUsernameInHeader() throws Exception {
        dashboardPresenter.attachView(dashboardView);
        dashboardPresenter.setUsernameInHeader("suppor@test.aasetowl.com");
        Mockito.verify(dashboardPresenter.getView()).setUsernameInHeader("suppor@test.aasetowl.com");
    }
    @Test
    public void setInitialiseInHeader() throws Exception {
        dashboardPresenter.attachView(dashboardView);
        dashboardPresenter.setInitialiseName("AS");
        Mockito.verify(dashboardPresenter.getView()).setInitialiseName("AS");
    }
}
