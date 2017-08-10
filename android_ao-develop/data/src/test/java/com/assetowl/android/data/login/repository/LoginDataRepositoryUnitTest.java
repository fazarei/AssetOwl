package com.assetowl.android.data.login.repository;

import android.content.SharedPreferences;

import com.assetowl.android.data.TestConstants;
import com.assetowl.android.data.localstorage.SessionLocalData;
import com.assetowl.android.data.login.api.Auth;
import com.assetowl.android.data.login.model.TermsAndCondition;
import com.assetowl.android.data.login.model.UserDataCredentials;
import com.assetowl.android.data.login.model.UserSession;
import com.assetowl.android.data.login.model.realm.LocalSessionInfo;
import com.assetowl.domain.login.repository.LoginRepository;
import com.assetowl.domain.session.model.SessionInfo;
import com.assetowl.domain.session.model.TermsAndConditionInfo;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by patrickyin on 15/2/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Retrofit.class, SessionLocalData.class, JSONObject.class})
public class LoginDataRepositoryUnitTest {

    private Retrofit retrofit;

    private LoginRepository loginRepository;
    @Mock
    private SharedPreferences sharedPreferences;
    @Mock
    private Observer<String> observer;
    @Mock
    private LoginDataRepository loginDataRepositoryMock;
    @Mock
    private LocalSessionInfo localSessionInfoMock;
    @Mock
    private Auth authMock;
    @Mock
    private Observable<Object> observableMock;
    @Mock
    private Observable<UserSession> observableSessionInfoMock;
    @Mock
    private Observable<TermsAndCondition> observableTermsAndConditionInfoMock;

    @Before
    public void setUp() throws Exception {
        retrofit = PowerMockito.mock(Retrofit.class);
        Mockito.when(retrofit.create(Auth.class)).thenReturn(authMock);
        when(sharedPreferences.getString(anyString(), anyString())).thenReturn(TestConstants.TEST_ACCOUNT_EMAIL);
        loginRepository = new LoginDataRepository(retrofit, sharedPreferences);
        PowerMockito.mockStatic(SessionLocalData.class);
    }

    @Test
    public void login() throws Exception {
        Mockito.when(authMock.login(any(RequestBody.class))).thenReturn(observableSessionInfoMock);
        Observable<SessionInfo> loginObservable = loginRepository.login(new UserDataCredentials(TestConstants.TEST_ACCOUNT_EMAIL, TestConstants.TEST_ACCOUNT_PASSWORD));
        loginObservable.subscribe(new Observer<SessionInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SessionInfo sessionInfo) {
                assertTrue(sessionInfo.getAccessToken().length() > 0);
                assertTrue(sessionInfo.getRefreshToken().length() > 0);
                assertTrue(sessionInfo.getAccessTokenExpiry() < System.currentTimeMillis());
            }
        });
    }

    @Test
    public void hasUserLoggedIn() throws Exception {
        Observable<String> hasLoggedInObsevable = loginRepository.hasUserLoggedIn();
        hasLoggedInObsevable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Mockito.verify(loginRepository.hasLoggedIn(), Mockito.times(1));
                if (loginRepository.hasLoggedIn() > 0)
                    assertEquals(s, "YES");
                else
                    assertEquals(s, "NO");
            }
        });
    }

    @Test
    public void logout() throws Exception {
        Observable logoutObservable = loginRepository.logout();
        logoutObservable.subscribe(new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Mockito.verify(loginRepository.clearTokenInfo(),Mockito.times(1));
                if(loginRepository.clearTokenInfo()==0)
                    assertTrue("NO",true);
                else
                    assertTrue("YES",true);
            }
        });
    }

    @Test
    public void logoutFromServerShouldReturnNullWhenThereIsNoLocalSessionInfo() throws Exception {
        when(SessionLocalData.fetchSessionInfoFromStorage()).thenReturn(null);
        assertNull(loginRepository.logoutFromServer());
    }

    @Test
    public void logoutFromServerShouldReturnObservableWhenThereIsValidLocalSessionInfo() throws Exception {
        Mockito.when(authMock.logout(any(RequestBody.class))).thenReturn(observableMock);
        when(SessionLocalData.fetchSessionInfoFromStorage()).thenReturn(localSessionInfoMock);
        assertThat(((LoginDataRepository)loginRepository).logoutFromServer(PowerMockito.mock(JSONObject.class)), instanceOf(Observable.class));
    }

    @Test
    public void termAndConditionContent() throws Exception {
        Mockito.when(authMock.termAndConditionContent(anyString())).thenReturn(observableTermsAndConditionInfoMock);
        Observable<TermsAndConditionInfo> termsAndConditionInfoObservable = loginRepository.termAndConditionContent();
        termsAndConditionInfoObservable.subscribe(new Observer<TermsAndConditionInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TermsAndConditionInfo termsAndConditionInfo) {
                assertTrue(termsAndConditionInfo.getTitle().length()>0);
                assertTrue(termsAndConditionInfo.getBody().length()>0);
            }
        });
    }

    @Test
    public void termsAndConditionAccept() throws Exception {
        Mockito.when(authMock.termAndConditionAccept(anyString(), anyInt())).thenReturn(observableSessionInfoMock);
        Observable<SessionInfo> termsAndConditionAcceptObservable = loginRepository.termAndConditionAccept();
        termsAndConditionAcceptObservable.subscribe(new Observer<SessionInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SessionInfo sessionInfo) {
                assertTrue(sessionInfo.getAccessToken().length()>0);
                assertTrue(sessionInfo.getRefreshToken().length()>0);
            }
        });
    }

    @Test
    public void shouldReturnLastLoggedInUsernameObservable() throws Exception {
        Object object = loginRepository.lastLoggedInUsername();
        assertThat(object, instanceOf(Observable.class));
    }

    @Test
    public void shouldReturnLastLoggedInUsernameToObserver() throws Exception {
        Observable<String> observable = loginRepository.lastLoggedInUsername();
        observable.subscribe(observer);
        verify(observer).onNext(TestConstants.TEST_ACCOUNT_EMAIL);
    }
}