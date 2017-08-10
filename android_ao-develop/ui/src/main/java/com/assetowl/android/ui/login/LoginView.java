package com.assetowl.android.ui.login;

import android.content.Context;

/**
 * Created by jamespott on 31/1/17.
 */

public interface LoginView {

    void setIncorrectUsernameOrPasswordError();

    void setPasswordError(boolean showError);

    void setUsernameError(boolean showError);

    void focusOnLastInvalidEntry();

    void showProgress();

    void hideProgress();

    void showError(String errorMsg);

    void initialiseView();

    void setConnectivityError();

    Context getContext();

    void setSigninButtonEnabled(boolean enabled);

    void switchToolbar(boolean connectionStatus);

    void launchDashboard();

    void setNetworkError();

    void setInvalidUsernameOrPasswordError();

    void clearError();

    void showTAndC(String title, String body);

    void declineTAndC();

    void setUsername(String username);
}
