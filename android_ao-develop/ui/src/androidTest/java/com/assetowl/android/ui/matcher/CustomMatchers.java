package com.assetowl.android.ui.matcher;

import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.action.ViewActions.actionWithAssertions;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by patrickyin on 8/3/17.
 */

public final class CustomMatchers {
    public static Matcher<View> noTextInputLayoutError() {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                return view instanceof TextInputLayout && !((TextInputLayout) view).isErrorEnabled();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("no error.");
            }
        };
    }

    public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with error: " + expectedErrorText);
            }
        };
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    public static Matcher<View> withTextColor(final int color) {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public boolean matchesSafely(TextView textView) {
                return (textView.getCurrentTextColor() == color);
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("has colors: " + color);
            }
        };
    }

    public static Matcher<View> withEmailInputType() {
        return new BoundedMatcher<View, EditText>(EditText.class) {

            @Override
            protected boolean matchesSafely(EditText item) {
                return (item.getInputType() & InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) > 0;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with error: The input type of the EditText is not Email Address input type.");
            }
        };
    }

    public static ViewAction replaceProgressBarDrawable() {
        return actionWithAssertions(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(ProgressBar.class);
            }

            @Override
            public String getDescription() {
                return "replace the ProgressBar drawable";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                ProgressBar progressBar = (ProgressBar) view;
                progressBar.setIndeterminateDrawable(new ColorDrawable(0xffff0000));
                uiController.loopMainThreadUntilIdle();
            }
        });
    }
}
