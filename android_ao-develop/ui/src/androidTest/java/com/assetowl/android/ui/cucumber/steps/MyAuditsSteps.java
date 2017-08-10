package com.assetowl.android.ui.cucumber.steps;

import android.support.test.espresso.ViewInteraction;
import android.test.ActivityInstrumentationTestCase2;

import com.assetowl.android.R;
import com.assetowl.android.ui.dashboard.DashboardActivity;
import com.assetowl.android.ui.matcher.CustomMatchers;
import com.assetowl.android.ui.matcher.RecyclerViewItemCountAssertion;

import org.junit.After;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static com.schibsted.spain.barista.BaristaSleepActions.sleep;
/**
 * Created by farzanehzarei on 11/5/17.
 */

public class MyAuditsSteps extends ActivityInstrumentationTestCase2<DashboardActivity> {

    public MyAuditsSteps(DashboardActivity dashboardActivity) {
        super(DashboardActivity.class);
    }

    @After
    public void tearDow() throws Throwable {
        getActivity().finish();
        super.tearDown();
    }

    @And("tap my audits menu item")
    public void tap_dashboard_menu_item() throws Throwable {
        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.my_audits), isDisplayed()))
                .perform(click());
        
        onView(withId(R.id.recycle_view_my_audits_list)).check(matches(isDisplayed()));
    }

    @Then("the list of my audits header are shown")
    public void the_list_of_my_audits_header_are_shown() throws Throwable {
        onView(withText(R.string.text_audits)).check(matches(isDisplayed()));
        onView(withText(R.string.text_new)).check(matches(isDisplayed()));
    }

    @And("Login shows my audits list in content area")
    public void Login_shows_my_audits_list_in_content_area() throws Throwable {
        onView(withId(R.id.recycle_view_my_audits_list)).check(matches(isDisplayed()));
    }

    @And("I should see My Audits menu option highlighted")
    public void I_should_see_My_Audits_menu_option_highlighted() throws Throwable {
        onView(allOf(withId(R.id.design_menu_item_text),withText(R.string.my_audits))).check(matches(isChecked()));
    }

    @Then("no audits show")
    public void no_audits_show() throws Throwable {
        onView(withId(R.id.recycle_view_my_audits_list))
                .check(new RecyclerViewItemCountAssertion(equalTo(0)));
    }

    @And("the welcome message is shown")
    public void the_welcome_message_is_shown() throws Throwable {
        onView(withText(R.string.welcome_to_assetowl)).check(matches(isDisplayed()));
        onView(withText(R.string.would_you_like_to)).check(matches(isDisplayed()));
        onView(withText(R.string.start_with_spaces)).check(matches(isDisplayed()));
        onView(withText(R.string.a_new_audit)).check(matches(isDisplayed()));
    }

    @And("tap filter (.+)")
    public void tap_filter(String selectedFilter) throws Throwable{
        onView(withText(getFilterStringResId(selectedFilter))).check(matches(isDisplayed())).perform(click());
    }

    @Then("the text of filter (.+) is correct color")
    public void the_text_of_filter_is_correct_color(String selectedFilter) throws Throwable{
        onView(withText(getFilterStringResId(selectedFilter)))
                .check(matches(CustomMatchers.withTextColor(getActivity().getResources().getColor(R.color.selector_tabitem))));
    }

    @Then("the columns (.+) are shown")
    public void the_columns_are_shown(String columns) throws Throwable {
        String[] headerColumns = columns.split(",");
        for(String headerColumn : headerColumns) {
            if(headerColumn.equals("submitted")) {
                onView(CustomMatchers.withIndex(withText(headerColumn), 1))
                        .check(matches(isDisplayed()));
            } else {
                onView(withText(headerColumn))
                        .check(matches(isDisplayed()));
            }
        }
    }


    private int getFilterStringResId(String filterName) {
        switch(filterName) {
            case "new_audits":
                return R.string.new_audits;
            case "in_progress":
                return R.string.in_progress;
            case "submitted":
                return R.string.submitted;
            default:
                return 0;
        }
    }

    @Then("the templates list page are shown")
    public void the_templates_list_page_are_shown() throws Exception {
        sleep(10_000);
        ViewInteraction recyclerView = onView(allOf(withId(R.id.recycler_view_templates_properties_audits_list), isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
    }

}
