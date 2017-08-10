package com.assetowl.android.ui.cucumber.steps;

import android.test.ActivityInstrumentationTestCase2;

import com.assetowl.android.R;
import com.assetowl.android.ui.dashboard.DashboardActivity;
import com.assetowl.android.ui.login.LoginActivity;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.BaristaSleepActions.sleep;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by farzanehzarei on 16/3/17.
 */

public class DashboardSteps extends ActivityInstrumentationTestCase2<DashboardActivity> {
    public DashboardSteps(DashboardActivity dashboardActivity) {
        super(DashboardActivity.class);
    }

    @After
    public void tearDown() throws Exception {
        getActivity().finish();
        super.tearDown();
    }

    @Given("a user has logged in")
    @Then("the app continues normally")
    public void a_user_has_logged_in() throws Exception {
        assertNotNull(getActivity());
    }

    @And("the nav drawer menu button is shown")
    public void the_nav_drawer_menu_button_is_shown() throws Throwable {
        onView(withId(R.id.tool_bar)).check(matches(isDisplayed()));
    }

    @And("the user opens the nav drawer")
    public void the_user_opens_the_nav_drawer() throws Throwable {

        onView(allOf(withContentDescription("open_drawer"), withParent(withId(R.id.tool_bar)), isDisplayed()))
                .perform(click());
        sleep(1_000);
    }

    @And("the drawer is shown")
    public void the_drawer_is_shown() throws Throwable {
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
    }

    @Then("the logout button is present")
    public void the_logout_button_is_present() throws Throwable {

        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.log_out), isDisplayed()));
    }

    @When("the user clicks logout")
    public void the_user_clicks_logout() throws Throwable {
        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.log_out), isDisplayed())).perform(click());
    }

    @Then("the user is taken to the login page")
    public void the_user_is_taken_to_the_login_page() throws Throwable {
        assertNotNull(LoginActivity.class);
    }

    @And("the user closes and opens the app")
    public void the_user_closes_and_opens_the_app() throws Throwable {
        getActivity().finish();
    }

    @Then("the user is at the dashboard page")
    public void the_user_is_at_the_dashboard_page() throws Throwable {
        assertTrue(getActivity().getComponentName().getClassName().equals(DashboardActivity.class.getName()));
    }

    @And("the header is red and email is shown")
    public void the_header_is_red_and_email_is_shown() throws Throwable {
        onView(withId(R.id.nav_header_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.name)).check(matches(isDisplayed()));
    }

    @And("the my audits is present")
    public void the_my_audits_is_present() throws Throwable {
        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.my_audits))).check(matches(isDisplayed()));
    }

    @And("the tutorial and app support and settings are present")
    public void the_tutorial_and_app_support_and_settings_are_present() throws Throwable {
        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.tutorials))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.app_support))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.settings))).check(matches(isDisplayed()));
    }

    @Then("the nav drawer close by tapping outside")
    public void the_nav_drawer_close_by_tapping_outside() throws Throwable {
        onView(withId(R.id.main_content)).check(matches(isDisplayed()));
        onView(withId(R.id.main_content)).perform(click());
    }

    @And("the nav drawer is open")
    public void the_nav_drawer_is_open() throws Throwable {
        onView(withId(R.id.tool_bar)).check(matches(isDisplayed()));
        onView(allOf(withContentDescription("open_drawer"), withParent(withId(R.id.tool_bar)), isDisplayed()))
                .perform(click());
        sleep(1_000);
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
    }
}
