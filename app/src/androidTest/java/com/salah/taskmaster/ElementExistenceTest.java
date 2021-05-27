package com.salah.taskmaster;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ElementExistenceTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void elementExistenceTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.main_setting_button), withText("Setting"),
                        withParent(allOf(withId(R.id.main_activity_displayed_username),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withText("TaskMaster"),
                        withParent(allOf(withId(R.id.action_bar),
                                withParent(withId(R.id.action_bar_container)))),
                        isDisplayed()));
        textView.check(matches(withText("TaskMaster")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textView7), withText("My Tasks"),
                        withParent(allOf(withId(R.id.main_activity_displayed_username),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        textView2.check(matches(withText("My Tasks")));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.add_task_button), withText("Add Tasks"),
                        withParent(allOf(withId(R.id.main_activity_displayed_username),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.all_tasks_button), withText("All Tasks"),
                        withParent(allOf(withId(R.id.main_activity_displayed_username),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.all_tasks_button), withText("All Tasks"),
                        withParent(allOf(withId(R.id.main_activity_displayed_username),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.add_task_button), withText("Add Tasks"),
                        childAtPosition(
                                allOf(withId(R.id.main_activity_displayed_username),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withText("Add Task"),
                        withParent(allOf(withId(R.id.action_bar),
                                withParent(withId(R.id.action_bar_container)))),
                        isDisplayed()));
        textView3.check(matches(withText("Add Task")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.editTextTextPersonName2), withText("Add Description for the Task"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        editText.check(matches(withText("Add Description for the Task")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextTextPersonName3), withText("add the state progress (“new”/“assigned”/“in progress”/“complete”)"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        editText2.check(matches(withText("add the state progress (“new”/“assigned”/“in progress”/“complete”)")));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.button3), withText("Add Task"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(
                allOf(withId(R.id.button3), withText("Add Task"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.main_setting_button), withText("Setting"),
                        childAtPosition(
                                allOf(withId(R.id.main_activity_displayed_username),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.setting_username), withText("username"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        editText3.check(matches(withText("username")));

        ViewInteraction button7 = onView(
                allOf(withId(R.id.setting_save_button), withText("save"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button7.check(matches(isDisplayed()));

        ViewInteraction button8 = onView(
                allOf(withId(R.id.setting_save_button), withText("save"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button8.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.all_tasks_button), withText("All Tasks"),
                        childAtPosition(
                                allOf(withId(R.id.main_activity_displayed_username),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withText("All Tasks"),
                        withParent(allOf(withId(R.id.action_bar),
                                withParent(withId(R.id.action_bar_container)))),
                        isDisplayed()));
        textView4.check(matches(withText("All Tasks")));

        ViewInteraction view = onView(
                allOf(withId(R.id.mockView2),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        view.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
