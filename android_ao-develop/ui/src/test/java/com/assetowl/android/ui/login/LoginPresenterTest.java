package com.assetowl.android.ui.login;

import com.assetowl.mvp.presenter.BasePresenter;
import com.assetowl.android.ui.login.observer.ConnectivityCheck;
import com.assetowl.android.ui.login.observer.LastLoggedInUsernameObserver;
import com.assetowl.android.ui.login.observer.LoginObserver;
import com.assetowl.android.ui.login.observer.TermAndConditionAcceptObserver;
import com.assetowl.android.ui.login.observer.TermAndConditionObserver;
import com.assetowl.domain.login.usecase.LastLoggedInUsernameUseCase;
import com.assetowl.domain.login.usecase.LoginUseCase;
import com.assetowl.domain.login.usecase.TermAndConditionAcceptUseCase;
import com.assetowl.domain.login.usecase.TermAndConditionUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by patrickyin on 8/3/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    private LoginPresenter loginPresenter;

    @Mock
    LoginUseCase loginUseCase;
    @Mock
    LoginObserver loginObserver;
    @Mock
    LoginView loginView;
    @Mock
    BasePresenter<LoginView> basePresenter;
    @Mock
    ConnectivityCheck connectivityCheck;
    @Mock
    TermAndConditionUseCase termAndConditionUseCase;
    @Mock
    TermAndConditionObserver termAndConditionObserver;
    @Mock
    TermAndConditionAcceptUseCase termAndConditionAcceptUseCase;
    @Mock
    TermAndConditionAcceptObserver termAndConditionAcceptObserver;
    @Mock
    LastLoggedInUsernameUseCase lastLoggedInUsernameUseCase;
    @Mock
    LastLoggedInUsernameObserver lastLoggedInUsernameObserver;

    @Before
    public void setUp() throws Exception {
        loginPresenter = new LoginPresenter(loginUseCase,
                loginObserver,
                connectivityCheck,
                termAndConditionUseCase,
                termAndConditionObserver,
                termAndConditionAcceptUseCase,
                termAndConditionAcceptObserver,
                lastLoggedInUsernameUseCase,
                lastLoggedInUsernameObserver);
    }

    @Test
    public void shouldInitialiseViewOnStart() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.start();
        Mockito.verify(loginPresenter.getView()).initialiseView();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowErrorForNullAttemptLogin() throws Exception {
        loginPresenter.attemptLogin(null, null);
    }

    @Test
    public void shouldShowErrorMessage() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.showError("test");
        Mockito.verify(loginPresenter.getView()).showError("test");
    }

    @Test
    public void shouldShowUsernameInvalidErrorWhenUsernameIsInvalid() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.attemptLogin("saddwdas", "tesew");
        Mockito.verify(loginPresenter.getView()).setUsernameError(true);
    }

    @Test
    public void shouldShowPasswordInvalidErrorWhenPasswordIsInvalid() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.attemptLogin("saddwdas@weqwe.com", "te");
        Mockito.verify(loginPresenter.getView()).setPasswordError(true);
    }

    @Test
    public void shouldNotShowUsernameInvalidErrorWhenUsernameIsValid() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.attemptLogin("saddwdas@dasasd.com", "tesew");
        Mockito.verify(loginPresenter.getView()).setUsernameError(false);
    }

    @Test
    public void shouldNotShowPasswordInvalidErrorWhenPasswordIsValid() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.attemptLogin("saddwdas@dasasd.com", "tesf34gregv3ew");
        Mockito.verify(loginPresenter.getView()).setPasswordError(false);
    }

    @Test
    public void shouldChangeFocusWhenUsernameOrPasswordIsNotValid() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.attemptLogin("test", "tes");
        Mockito.verify(loginPresenter.getView(), Mockito.atLeastOnce()).focusOnLastInvalidEntry();
    }

    @Test
    public void showShowDisconnectMessage() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.showDisconnectMessage(false);
        Mockito.verify(loginPresenter.getView()).switchToolbar(false);
    }

    @Test
    public void shoudMoveToDashboard() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.moveToDashboard();
        Mockito.verify(loginPresenter.getView()).launchDashboard();
    }

    @Test
    public void shoudSetNetworkError() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.setNetworkError();
        Mockito.verify(loginPresenter.getView()).setNetworkError();
    }

    @Test
    public void shoudSetInvalidUsernameOrPasswordError() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.setInvalidUsernameOrPasswordError();
        Mockito.verify(loginPresenter.getView()).setInvalidUsernameOrPasswordError();
    }

    @Test
    public void shouldClearError() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.clearError();
        Mockito.verify(loginPresenter.getView()).clearError();
    }

    @Test
    public void showTAndCContent() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.showTAndCContent("Title", "Body");
        Mockito.verify(loginPresenter.getView()).showTAndC("Title", "Body");
    }

    @Test
    public void shouldSetDefaultUserName() throws Exception {
        String username = "test@test.test";
        loginPresenter.attachView(loginView);
        loginPresenter.setDefaultUsername(username);
        Mockito.verify(loginPresenter.getView()).setUsername(username);
    }

    @Test
    public void shouldExecuteLastLoggedInUsernameUseCaseWhenViewOnCreate() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.checkDefaultUsername();
        Mockito.verify(lastLoggedInUsernameUseCase).execute(lastLoggedInUsernameObserver);
    }

    @Test
    public void shouldShowProgressBarWhenPerformLoginMethod() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.attemptLogin("aa@aa.com", "aaaaaaaaaa");
        Mockito.verify(loginPresenter.getView()).showProgress();
    }

    @Test
    public void shouldHideProgressBarWhenPerformShowErrorMethod() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.showError("");
        Mockito.verify(loginPresenter.getView()).hideProgress();
    }

    @Test
    public void shouldHideProgressBarWhenPerformSetNetworkErrorMethod() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.setNetworkError();
        Mockito.verify(loginPresenter.getView()).hideProgress();
    }

    @Test
    public void shouldHideProgressBarWhenPerformSetInvalidUsernameOrPasswordErrorMethod() throws Exception {
        loginPresenter.attachView(loginView);
        loginPresenter.setInvalidUsernameOrPasswordError();
        Mockito.verify(loginPresenter.getView()).hideProgress();
    }
}