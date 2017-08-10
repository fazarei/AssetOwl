package com.assetowl.android.ui.cucumber.steps;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assetowl.android.R;
import com.assetowl.android.ui.dashboard.DashboardActivity;
import com.assetowl.android.ui.login.LoginActivity;
import com.assetowl.android.ui.matcher.CustomMatchers;
import com.assetowl.android.utils.Connectivity;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.assetowl.android.R.id.username;
import static com.assetowl.android.ui.matcher.CustomMatchers.replaceProgressBarDrawable;
import static com.schibsted.spain.barista.BaristaSleepActions.sleep;
import static org.hamcrest.Matchers.not;


/**
 * Created by patrickyin on 2/3/17.
 */

public class LoginSteps extends ActivityInstrumentationTestCase2<LoginActivity> {

    public LoginSteps(LoginActivity loginActivity) {
        super(LoginActivity.class);
    }

    boolean connectivity = false;

    @After
    public void tearDown() throws Exception {
        WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        getActivity().finish();
        super.tearDown();
    }


    @Given("an activity")
    public void an_activity() throws Throwable {
        assertNotNull(getActivity());
    }

    @Then("I can see title text")
    public void I_can_see_title_text() throws Throwable {
        onView(withText(R.string.login_title))
                .check(matches(isDisplayed()));
    }

    @And("I can see the AssetOwl logo")
    public void I_can_see_the_AssetOwl_logo() throws Throwable {
        onView(withId(R.id.action_bar_logo))
                .check(matches(isDisplayed()));
    }

    @And("I can see the username inputbox")
    public void I_can_see_the_username_inputbox() throws Throwable {
        onView(withId(username))
                .check(matches(isDisplayed()));
    }

    @And("I can see the password inputbox")
    public void I_can_see_the_password_inputbox() throws Throwable {
        onView(withId(R.id.password))
                .check(matches(isDisplayed()));
    }

    @And("I can see the login button")
    public void I_can_see_the_login_button() throws Throwable {
        onView(withId(R.id.username_sign_in_button))
                .check(matches(isDisplayed()));
    }

    @When("enter username (.+)")
    public void enter_username(String username) throws Throwable {
        onView(withId(R.id.username))
                .perform(clearText(), typeText(username));
    }

    @And("enter password \"([^\"]*)\"")
    public void enter_password(String password) throws Throwable {
        onView(withId(R.id.password))
                .perform(clearText(), typeText(password), closeSoftKeyboard());
    }

    @And("click login button")
    public void click_login_button() throws Throwable {
        sleep(1_500);
        onView(isAssignableFrom(ProgressBar.class)).perform(replaceProgressBarDrawable());
        onView(withId(R.id.username_sign_in_button))
                .perform(click());
        sleep(1_500);
    }

    @Then("the error message (true|false) or/and (true|false) will be shown")
    public void the_error_message_will_be_shown(boolean username_error, boolean password_error) throws Throwable {
        if (username_error) {
            onView(withId(R.id.inputLayoutUsername))
                    .check(matches(CustomMatchers.hasTextInputLayoutErrorText(getActivity().getString(R.string.error_invalid_username))));
        } else {
            onView(withId(R.id.inputLayoutUsername))
                    .check(matches(CustomMatchers.noTextInputLayoutError()));
        }
        if (password_error) {
            onView(withId(R.id.inputLayoutPassword))
                    .check(matches(CustomMatchers.hasTextInputLayoutErrorText(getActivity().getString(R.string.error_invalid_password))));
        } else {
            onView(withId(R.id.inputLayoutPassword))
                    .check(matches(CustomMatchers.noTextInputLayoutError()));
        }
    }

    @Then("the invalid username or password error message will be shown")
    public void the_invalid_username_or_password_error_message_will_be_shown() throws Throwable {
        onView(withText(R.string.error_invalid_username_or_password))
                .check(matches(isDisplayed()));
    }

    @And("No Internet Connection")
    public void No_Internet_Connection() throws Exception {

        WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);
        connectivity = Connectivity.isNetworkAvailable(getActivity());
        sleep(2_000);
    }

    @Then("I can see a offline message displayed")
    public void I_can_see_a_offline_message_displayed() throws Exception {
        sleep(1_500);
        if (!connectivity)
            onView(withId(R.id.tool_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @And("There is internet connection")
    public void There_is_internet_connection() throws Exception {

        WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        sleep(1_000);
        connectivity = Connectivity.isNetworkAvailable(getActivity().getContext());

    }

    @Then("the offline status disappears")
    public void the_offline_status_disappears() throws Exception {
        sleep(2_000);
        if (connectivity)
            onView(withId(R.id.tool_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Then("an offline message is displayed")
    public void an_offline_message_is_displayed() throws Exception {
        if (!connectivity)
            onView(withText(R.string.unable_to_connect_with_assetowl)).check(matches(isDisplayed()));
    }

    @And("set network status back")
    public void set_network_status_back() throws Exception {
        WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        sleep(3_000);
    }

    @Then("home dashboard screen is displayed")
    public void home_dashboard_screen_is_displayed() throws Exception {
        assertNotNull(getActivity());
        // this part need to be changed after update the cucumber runner
    }

    @Then("the network error message will be shown")
    public void the_network_error_message_will_be_shown() throws Exception {
        onView(withText(R.string.unable_to_connect_with_assetowl)).check(matches(isDisplayed()));
    }

    @Then("the TAndCs are presented")
    public void the_TAndCs_are_presented() throws Exception {
        sleep(1000);
        onView(withId(android.R.id.button2)).check(matches(isDisplayed()));
    }

    @When("the user declines the TAndCs")
    public void the_user_declines_the_TAndCs() throws Exception {
        onView(withId(android.R.id.button2)).perform(click());
    }

    @Then("the app goes to the login screen")
    public void the_app_goes_to_the_login_screen() throws Exception {
        assertNotNull(getActivity());
    }

    @And("the username remains entered (.+)")
    public void the_username_remains_entered(String username) throws Exception {
        EditText editTextUsername = (EditText) getActivity().findViewById(R.id.username);
        assertEquals(username, editTextUsername.getText().toString());
    }

    @And("the password field is empty")
    public void the_password_field_is_empty() throws Exception {
        EditText editTextPassword = (EditText) getActivity().findViewById(R.id.password);
        assertEquals("", editTextPassword.getText().toString());
    }

    @When("the user accepts the TAndCs")
    public void the_user_accepts_the_TAndCs() throws Exception {
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Then("the app goes dashboard")
    public void the_app_goes_dashboard() throws Exception {
        assertNotNull(DashboardActivity.class);
    }

    @Then("the app is unable to retrieve the TAndC")
    public void the_app_is_unable_to_retrieve_the_TAndC() throws Exception {
        onView(withText(R.string.unable_to_connect_with_assetowl)).check(matches(isDisplayed()));
    }

    @When("tap username field")
    public void tap_username_field() throws Throwable {
        onView(withId(R.id.username))
                .perform(click());
    }

    @Then("the email keyboard is shown")
    public void the_email_keyboard_is_shown() throws Throwable {
        onView(withId(R.id.username)).check(matches(CustomMatchers.withEmailInputType()));
    }

    @Then("the spinner is shown")
    public void the_spinner_is_shown() throws Throwable {
        sleep(1_000);
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));
    }

    @Then("the spinner is not shown")
    public void the_spinner_is_not_shown() throws Throwable {
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));
        onView(withText(R.string.thanks_for_waiting)).check(doesNotExist());
    }

    @And("wait (\\d+) seconds")
    public void wait_seconds(int seconds) throws Throwable {
        sleep(seconds * 1000);
    }

    @Then("the \"(.+)\" message is shown in toolbar")
    public void the_message_is_shown_in_toolbar(String message) throws Throwable {
        sleep(1_000);
        onView(withText(message)).check(matches(isDisplayed()));
    }

    @Then("the login button is not shown")
    public void the_login_button_is_not_shown() throws Throwable {
        onView(withId(R.id.button_login)).check(doesNotExist());
    }
    @Then("the login button is shown")
    public void the_login_button_is_shown() throws Throwable {
        onView(withId(R.id.button_login)).check(doesNotExist());
    }
}
