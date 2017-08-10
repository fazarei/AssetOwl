package com.assetowl.android.ui.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assetowl.android.AssetOwlApplication;
import com.assetowl.android.R;
import com.assetowl.android.mvp.ui.PresenterActivity;
import com.assetowl.android.ui.component.actionbar.ActionBarManager;
import com.assetowl.android.ui.component.spinner.LoadingSpinner;
import com.assetowl.android.ui.dashboard.DashboardActivity;
import com.assetowl.android.ui.login.di.component.LoginComponent;
import com.assetowl.android.utils.KeyboardUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends PresenterActivity<LoginPresenter> implements LoginView {

    @Inject
    protected ActionBarManager actionBarManager;

    @Bind(R.id.username)
    TextInputEditText usernameView;
    @Bind(R.id.password)
    TextInputEditText passwordView;

    @Bind(R.id.inputLayoutUsername)
    TextInputLayout inputLayoutUsernameView;
    @Bind(R.id.inputLayoutPassword)
    TextInputLayout inputLayoutPasswordView;

    @Bind(R.id.login_form)
    View loginFormView;

    @Bind(R.id.txt_error_message)
    TextView errorMessageTextField;

    @Bind(R.id.username_sign_in_button)
    Button signInButton;

    @Bind(R.id.tool_bar)
    RelativeLayout toolbar;

    @Bind(R.id.login_loading_spinner)
    LoadingSpinner loadingSpinner;

    private View focusView;
    private LoginComponent loginComponent;

    @Override
    protected void initialiseInjector() {
        final AssetOwlApplication application = (AssetOwlApplication) getApplicationContext();
        loginComponent = application.getComponentFactory().createLoginComponent(getActivityModule());
        loginComponent.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.checkDefaultUsername();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    private void initialisePasswordActionListener() {
        passwordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            attemptLogin();
            return true;
        });
    }

    private void initialiseCustomActionBar() {
        actionBarManager.initAssetOwlActionBar(R.string.login_title);
    }

    private void initialiseSpinner() {
        loadingSpinner.setText(R.string.loading_assetowl);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.username_sign_in_button)
    void attemptLogin() {
        KeyboardUtils.hideKeyboard(this);
        presenter.attemptLogin(getUsername(), getPassword());
    }

    @NonNull
    private String getPassword() {
        return passwordView.getText().toString();
    }

    @NonNull
    private String getUsername() {
        return usernameView.getText().toString().trim();
    }

    @Override
    public void setPasswordError(boolean showError) {
        if (showError) {
            inputLayoutPasswordView.setErrorEnabled(true);
            inputLayoutPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
        } else {
            inputLayoutPasswordView.setErrorEnabled(false);
        }
    }

    @Override
    public void setUsernameError(boolean showError) {
        if (showError) {
            inputLayoutUsernameView.setErrorEnabled(true);
            inputLayoutUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = usernameView;
        } else {
            inputLayoutUsernameView.setErrorEnabled(false);
        }
    }

    @Override
    public void setIncorrectUsernameOrPasswordError() {

    }

    @Override
    public void focusOnLastInvalidEntry() {
        if (focusView != null) focusView.requestFocus();
    }

    @Override
    public void showProgress() {
        setSigninButtonEnabled(false);
        actionBarManager.setTitle(R.string.thanks_for_waiting);
        loadingSpinner.show();
    }

    @Override
    public void hideProgress() {
        setSigninButtonEnabled(true);
        actionBarManager.setTitle(R.string.login_title);
        loadingSpinner.hide();
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void initialiseView() {
        initialiseCustomActionBar();
        initialisePasswordActionListener();
        initialiseSpinner();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void setConnectivityError() {
        errorMessageTextField.setText(R.string.unable_to_connect_with_assetowl);
    }

    @Override
    public void setSigninButtonEnabled(boolean enabled) {
        signInButton.setEnabled(enabled);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.safelyUnsubscribe();
    }

    @Override
    public void launchDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public void switchToolbar(boolean connectionStatus) {
        if (connectionStatus) {
            getSupportActionBar().show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorStatusBarBackground));
            }
            toolbar.setVisibility(View.GONE);
        } else {
            getSupportActionBar().hide();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_dark_color));
            }
            toolbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setNetworkError() {
        errorMessageTextField.setText(R.string.unable_to_connect_with_assetowl);
    }

    @Override
    public void setInvalidUsernameOrPasswordError() {
        errorMessageTextField.setText(R.string.error_invalid_username_or_password);
    }

    @Override
    public void clearError() {
        errorMessageTextField.setText("");
    }

    @Override
    public void showTAndC(String title, String body) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        WebView tAndCWebView = new WebView(this);

        String htmlDocument = "<html><head></head><body>" + body + "</body></html>";
        tAndCWebView.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null);

        alert.setView(tAndCWebView);
        alert.setNegativeButton(R.string.decline, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                presenter.declineTAndC();
            }
        });

        alert.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                presenter.tAndCAccept();
            }
        });

        alert.show();
    }

    @Override
    public void declineTAndC() {
        passwordView.setText("");
    }

    @Override
    public void setUsername(String username) {
        usernameView.setText("");
        usernameView.append(username);
    }
}

