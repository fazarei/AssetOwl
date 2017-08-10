package com.assetowl.domain.login.repository;

import com.assetowl.domain.login.model.UserCredentials;
import com.assetowl.domain.login.repository.LoggedInDescriptor.LoggedInDef;
import com.assetowl.domain.session.model.SessionInfo;
import com.assetowl.domain.session.model.TermsAndConditionInfo;

import rx.Observable;

/**
 * Login Repository contract implemented by data layer
 * <p>
 * Created by jamespott on 7/2/17.
 */

public interface LoginRepository {

    Observable<SessionInfo> login(UserCredentials userCredentials);

    @LoggedInDef
    Observable<String> hasUserLoggedIn();

    @LoggedInDef
    Observable logout();

    Observable<Object> logoutFromServer();

    @LoggedInDef
    Observable<TermsAndConditionInfo> termAndConditionContent();

    @LoggedInDef
    Observable<SessionInfo> termAndConditionAccept();

    Observable<String> lastLoggedInUsername();

    int hasLoggedIn();

    int clearTokenInfo();

    Observable<String> initialName();
}
