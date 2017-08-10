package com.assetowl.android.ui.cucumber.steps;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.test.ActivityInstrumentationTestCase2;

import com.assetowl.android.R;
import com.assetowl.android.ui.dashboard.DashboardActivity;
import com.assetowl.android.ui.matcher.RecyclerViewItemCountAssertion;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.BaristaSleepActions.sleep;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;

/**
 * Created by patrickyin on 9/5/17.
 */

public class TemplatesListSteps extends ActivityInstrumentationTestCase2<DashboardActivity> {

    public TemplatesListSteps(DashboardActivity dashboardActivity) {
        super(DashboardActivity.class);
    }

    @After
    public void tearDown() throws Exception {
        getActivity().finish();
        super.tearDown();
    }

    @And("tap fab button in my aduit page")
    public void tap_fab_button_in_my_aduit_page() throws Exception {
        onView(withId(R.id.my_audits_button_create_audit)).perform(click());
    }

    @Then("the header of audit are shown")
    public void the_header_of_audit_are_shown() throws Exception {
    }

    @Then("the templates list are shown")
    public void the_templates_list_are_shown() throws Exception {
        sleep(10_000);
        ViewInteraction recyclerView = onView(allOf(withId(R.id.recycler_view_templates_properties_audits_list), isDisplayed()));
        recyclerView.check(new RecyclerViewItemCountAssertion(greaterThan(1)));
    }

    @And("tab bar is shown step 1")
    public void tab_bar_is_shown_step_1() throws Exception {
        onView(withText(R.string.step1_select_template)).check(matches(isDisplayed()));
    }

    @And("template name is shown")
    public void template_name_is_shown() throws Exception {
        allOf(withId(R.id.textview_audit_templates), isDisplayed());
    }

    @And("number of question is shown")
    public void number_of_question_is_shown() throws Exception {
        allOf(withId(R.id.textview_questions), isDisplayed());
    }

    @And("template id is shown")
    public void template_id_is_shown() throws Exception {
        allOf(withId(R.id.textview_id), isDisplayed());
    }

    @And("user click on one item")
    public void user_click_on_one_item() throws Exception {
        ViewInteraction recyclerView = onView(allOf(withId(R.id.recycler_view_templates_properties_audits_list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(1,click()));
    }

    @And("user click on multiple template")
    public void user_click_on_multiple_template() throws Exception {
        ViewInteraction recyclerView = onView(allOf(withId(R.id.recycler_view_templates_properties_audits_list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(3, click()));
        recyclerView.perform(actionOnItemAtPosition(2, click()));
        recyclerView.perform(actionOnItemAtPosition(1, click()));
    }

    @Then("next button is enable")
    public void next_button_is_enable() throws Exception {
        onView(withId(R.id.btn_next)).check(matches(isEnabled()));
    }

    @Then("next button is disable")
    public void next_button_is_disable() throws Exception {
        onView(withId(R.id.btn_next)).check(matches(not(isEnabled())));
    }

    @And("select x button")
    public void select_x_button() throws Exception {
        onView(withId(R.id.img_close)).perform(click());
    }

    @Then("return to my audit screen")
    public void return_to_my_audit_screen() throws Exception {
        onView(withId(R.id.recycle_view_my_audits_list)).check(matches(isDisplayed()));
    }
}
