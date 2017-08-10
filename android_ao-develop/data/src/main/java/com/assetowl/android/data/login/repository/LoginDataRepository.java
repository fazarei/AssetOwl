package com.assetowl.android.data.login.repository;


import android.content.SharedPreferences;

import com.assetowl.android.data.localstorage.SessionLocalData;
import com.assetowl.android.data.login.api.Auth;
import com.assetowl.android.data.login.model.TermAndConditionCredentials;
import com.assetowl.android.data.login.model.TermsAndCondition;
import com.assetowl.android.data.login.model.UserSession;
import com.assetowl.android.data.login.model.realm.LocalSessionInfo;
import com.assetowl.android.data.utils.HTTPRequestBodyHelper;
import com.assetowl.domain.login.model.UserCredentials;
import com.assetowl.domain.login.repository.LoggedInDescriptor.LoggedInDef;
import com.assetowl.domain.login.repository.LoggedInStatus;
import com.assetowl.domain.login.repository.LoginRepository;
import com.assetowl.domain.session.model.SessionInfo;
import com.assetowl.domain.session.model.TermsAndConditionInfo;

import org.json.JSONObject;

import javax.inject.Inject;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.identity.Registration;
import io.realm.Realm;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by jamespott on 8/2/17.
 */

public class LoginDataRepository implements LoginRepository {

    private final Auth authService;
    private Realm realm;
    private final SharedPreferences sharedPreferences;
    private TermAndConditionCredentials termAndConditionCredentials = new TermAndConditionCredentials();
    private final static String KEY_REMEMBER_USERNAME = "REMEMBER_USERNAME";

    @Inject
    LoginDataRepository(Retrofit retrofit, SharedPreferences sharedPreferences) {
        authService = retrofit.create(Auth.class);
        this.sharedPreferences = sharedPreferences;
    }


    @Override
    public Observable<SessionInfo> login(final UserCredentials userCredentials) {

        return authService.login(HTTPRequestBodyHelper.fromGson(userCredentials)).map(new Func1<UserSession, SessionInfo>() {
            @Override
            public SessionInfo call(final UserSession userSession) {
                final SessionInfo sessionInfo = new SessionInfo() {
                    @Override
                    public String getAccessToken() {
                        return userSession.getAccessToken();
                    }

                    @Override
                    public String getRefreshToken() {
                        return userSession.getRefreshToken();
                    }

                    @Override
                    public Long getAccessTokenExpiry() {
                        return userSession.getAccessTokenExpiry();
                    }

                    @Override
                    public String getEmail() {
                        return userCredentials.getUsername();
                    }

                    @Override
                    public boolean getTosAccepted() {
                        return userSession.getTosAccepted();
                    }

                    @Override
                    public String getFirstName() {
                        return userSession.getFirstName();
                    }

                    @Override
                    public String getLastName() {
                        return userSession.getLastName();
                    }
                };
                registerIntercom(userCredentials.getUsername());
                if (userSession.getTosAccepted()) {
                    storeSessionInfoInRealm(sessionInfo);
                }
                rememberUsername(sessionInfo);

                termAndConditionCredentials.setUsername(userCredentials.getUsername());
                termAndConditionCredentials.setAccessToken(sessionInfo.getAccessToken());
                return sessionInfo;
            }
        });
    }

    @Override
    @LoggedInDef
    public Observable<String> hasUserLoggedIn() {

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (hasLoggedIn() > 0)
                        subscriber.onNext(LoggedInStatus.YES);
                    else
                        subscriber.onNext(LoggedInStatus.NO);

                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
        return observable;
    }

    @Override
    @LoggedInDef
    public Observable logout() {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (clearTokenInfo() == 0) {
                        subscriber.onNext(LoggedInStatus.NO);
                    } else {
                        subscriber.onNext(LoggedInStatus.YES);
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        });
        return observable;
    }

    @Override
    public Observable<Object> logoutFromServer() {
        return logoutFromServer(new JSONObject());
    }

    public Observable<Object> logoutFromServer(JSONObject parametersJsonObject) {
        LocalSessionInfo localSessionInfo = SessionLocalData.fetchSessionInfoFromStorage();
        if (localSessionInfo != null) {
            try {
                parametersJsonObject.put("refreshToken", localSessionInfo.getRefreshToken());
            } catch (Exception e) {}

            return authService.logout(HTTPRequestBodyHelper.fromJSONObject(parametersJsonObject))
                    .doOnCompleted(new Action0() {
                        @Override
                        public void call() {
                            unregisterIntercom();
                        }
                    });
        } else {
            return null;
        }
    }

    @Override
    public Observable<TermsAndConditionInfo> termAndConditionContent() {

        return authService.termAndConditionContent(termAndConditionCredentials.getAccessToken()).map(new Func1<TermsAndCondition, TermsAndConditionInfo>() {
            @Override
            public TermsAndConditionInfo call(final TermsAndCondition termsAndCondition) {

                final TermsAndConditionInfo termsAndConditionInfo = new TermsAndConditionInfo() {
                    @Override
                    public int getVersion() {
                        return termsAndCondition.getVersion();
                    }

                    @Override
                    public String getContentType() {
                        return termsAndCondition.getContentType();
                    }

                    @Override
                    public String getTitle() {
                        return termsAndCondition.getTitle();
                    }

                    @Override
                    public String getBody() {
                        return termsAndCondition.getBody();
                    }
                };
                termAndConditionCredentials.setVersionNumber(termsAndConditionInfo.getVersion());
                return termsAndConditionInfo;
            }
        });
    }

    @Override
    public Observable termAndConditionAccept() {
        return authService.termAndConditionAccept(termAndConditionCredentials.getAccessToken(), termAndConditionCredentials.getVersionNumber())
                .doOnNext(new Action1<UserSession>() {
                    @Override
                    public void call(final UserSession userSession) {
                        storeSessionInfoInRealm(new SessionInfo() {
                            @Override
                            public String getAccessToken() {
                                return userSession.getAccessToken();
                            }

                            @Override
                            public String getRefreshToken() {
                                return userSession.getRefreshToken();
                            }

                            @Override
                            public Long getAccessTokenExpiry() {
                                return userSession.getAccessTokenExpiry();
                            }

                            @Override
                            public String getEmail() {
                                return null;
                            }

                            @Override
                            public boolean getTosAccepted() {
                                return false;
                            }

                            @Override
                            public String getFirstName() {
                                return userSession.getFirstName();
                            }

                            @Override
                            public String getLastName() {
                                return userSession.getLastName();
                            }
                        });
                    }
                });
    }

    @Override
    public Observable<String> lastLoggedInUsername() {
        LocalSessionInfo localSessionInfo = SessionLocalData.fetchSessionInfoFromStorage();
        return Observable.just(sharedPreferences.getString(KEY_REMEMBER_USERNAME, ""));
    }

    @Override
    public Observable<String> initialName() {
        LocalSessionInfo localSessionInfo = SessionLocalData.fetchSessionInfoFromStorage();
        if(localSessionInfo!=null) {
            String firstName = localSessionInfo.getFirstName().substring(0, 1);
            String lastName = localSessionInfo.getLastName().substring(0, 1);
            return Observable.just(firstName + lastName);
        }
        return Observable.just("");
    }


    private void rememberUsername(SessionInfo sessionInfo) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_REMEMBER_USERNAME, sessionInfo.getEmail());
        editor.apply();
    }

    private void storeSessionInfoInRealm(SessionInfo sessionInfo) {

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        LocalSessionInfo localSessionInfo = realm.createObject(LocalSessionInfo.class);

        localSessionInfo.setAccessToken(sessionInfo.getAccessToken());
        localSessionInfo.setRefreshToken(sessionInfo.getRefreshToken());
        localSessionInfo.setAccessTokenExpiry(sessionInfo.getAccessTokenExpiry());
        localSessionInfo.setFirstName(sessionInfo.getFirstName());
        localSessionInfo.setLastName(sessionInfo.getLastName());

        realm.commitTransaction();

        long count = realm.where(LocalSessionInfo.class).count();
        realm.close();
    }

    public int hasLoggedIn() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        long count = realm.where(LocalSessionInfo.class).count();
        realm.close();
        return (int) count;
    }

    public int clearTokenInfo() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(LocalSessionInfo.class);
        realm.commitTransaction();
        long count = realm.where(LocalSessionInfo.class).count();
        realm.close();
        return (int) count;
    }

    private void registerIntercom(String username) {
        Intercom.client().registerIdentifiedUser(Registration.create().withEmail(username).withUserId(username));
    }

    private void unregisterIntercom() {
        Intercom.client().reset();
    }

}
