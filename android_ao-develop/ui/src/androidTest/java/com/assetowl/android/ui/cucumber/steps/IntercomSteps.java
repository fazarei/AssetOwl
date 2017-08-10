package com.assetowl.android.ui.cucumber.steps;

import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;

import com.assetowl.android.R;
import com.assetowl.android.data.login.repository.LoginAnalyticsRepository;
import com.assetowl.android.ui.dashboard.DashboardActivity;
import com.assetowl.domain.login.analytics.builder.LoginAnalyticsEventBuilder;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.identity.Registration;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.BaristaSleepActions.sleep;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by patrickyin on 4/4/17.
 */

public class IntercomSteps extends ActivityInstrumentationTestCase2<DashboardActivity> {
    private int childCount = 0;

    public IntercomSteps(DashboardActivity dashboardActivity) {
        super(DashboardActivity.class);
    }

    @After
    public void tearDown() throws Exception {
        getActivity().finish();
        super.tearDown();
    }

    @And("the help button is shown")
    public void the_help_button_is_shown() throws Throwable {
        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.app_support), isDisplayed()));
    }

    @And("the user clicks the help button")
    public void the_user_clicks_the_help_button() throws Throwable {

        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.app_support), isDisplayed()))
                .perform(click());
    }

    @Then("the intercom chat window opens")
    public void the_intercom_chat_window_opens() throws Throwable {

        Intercom.client().registerIdentifiedUser(Registration.create().withUserId("support@test.assetowl.com"));
        sleep(800);
        onView(allOf(withId(R.id.input_text), withParent(withId(R.id.composer_edit_text_layout)), isDisplayed()));
    }

    @Then("the user sends a message")
    public void the_user_sends_a_message() throws Throwable {

        if (android.os.Build.VERSION.SDK_INT != 22) {
            sleep(1_500);
            onView(withId(R.id.compose_action_button)).check(matches(isDisplayed()))
                    .perform(click());
            sleep(300);
            onView(allOf(withId(R.id.input_text), withParent(withId(R.id.composer_edit_text_layout)), isDisplayed()))
                    .perform(typeText("Test Intercom"), closeSoftKeyboard());
            onView(allOf(withId(R.id.send_button), withContentDescription("Send button"), isDisplayed()))
                    .perform(click());
            sleep(300);
        }
    }

    @When("the user closes the chat window")
    public void the_user_closes_the_chat_window() throws Throwable {

        onView(allOf(withId(R.id.intercom_toolbar_close), isDisplayed()))
                .perform(click());
    }

    @Then("the user sees the screen they had before opening the intercom chat window")
    public void the_user_sees_the_screen_they_had_before_opening_the_intercom_chat_window() throws Throwable {
        assertTrue(getActivity().getComponentName().getClassName().equals(DashboardActivity.class.getName()));
    }


    @And("prepare for catch view changes")
    public void prepare_for_catch_view_changes() throws Throwable {
        ViewGroup decor = (ViewGroup)getActivity().getWindow().getDecorView();
        childCount = decor.getChildCount();
    }

    @And("no message is shown to the user")
    public void no_message_is_shown_to_the_user() throws Throwable {
        ViewGroup decor = (ViewGroup)getActivity().getWindow().getDecorView();
        assertEquals("a new view appears.", childCount, decor.getChildCount());
    }

    @And("send a login event")
    public void send_a_login_event() throws Throwable {
        new LoginAnalyticsRepository().push(new LoginAnalyticsEventBuilder()
                .add("email", "support@test.assetowl.com")
                .build());
    }

}