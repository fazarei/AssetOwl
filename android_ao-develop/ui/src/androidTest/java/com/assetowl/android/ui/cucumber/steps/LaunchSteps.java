package com.assetowl.android.ui.cucumber.steps;

import android.test.ActivityInstrumentationTestCase2;

import com.assetowl.android.R;
import com.assetowl.android.ui.launch.LaunchActivity;
import com.assetowl.android.utils.Connectivity;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by farzanehzarei on 3/3/17.
 */
public class LaunchSteps extends ActivityInstrumentationTestCase2<LaunchActivity> {

    Boolean connectivity = false;

    public LaunchSteps(LaunchActivity launchActivity) {
        super(LaunchActivity.class);
    }

    @After
    public void tearDown() throws Exception {
        getActivity().finish();
        super.tearDown();
    }

    @Given("launch Activity")
    public void launch_Activity() throws Exception {
        assertNotNull(getActivity());
    }

    @And("There is no internet connection")
    public void There_is_no_internet_connection() throws Exception {
        connectivity = Connectivity.isNetworkAvailable(getActivity());

    }

    @And("I can see a network error screen displayed as per designs")
    public void I_can_see_a_network_error_screen_displayed_as_per_designs() throws Exception {
        if (!connectivity) {
            onView(withId(R.id.txt_no_connectivity_first_line)).check(matches(isDisplayed()));
            onView(withId(R.id.txt_no_connectivity_second_line)).check(matches(isDisplayed()));
        }
    }

    @And("I can see a Ok button enabled")
    public void I_can_see_a_Ok_button_enabled() throws Exception {
        if (!connectivity)
            onView(withId(R.id.button_exit)).check(matches(isEnabled()));
    }

    @Then("I close no internet connection error screen")
    public void I_close_no_internet_connection_error_screen() throws Exception {
        if (!connectivity)
            onView(withId(R.id.button_exit)).perform(click());
    }

}
