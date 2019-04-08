package com.example.cyparking;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.DrawerMatchers;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.RootMatchers;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DashboardActivityTest {

    @Rule
    public ActivityTestRule<DashboardActivity> mActivityTestRule = new ActivityTestRule<>(DashboardActivity.class);

    private String testToken = "e421ab62-6648-455d-ae71-b51f7574f9ae";
    private String expected_username = "test10";
    private String expected_email = "test10@gmail.com";

    @Before
    public void setup() throws Exception {
        Intents.init();
        mActivityTestRule.getActivity().userToken = testToken;
    }
    /*

    @Test
    public void userInfoHasLoaded() {
        // Open Drawer to click on navigation.
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
                .check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        Espresso.onView((ViewMatchers.withId(R.id.header_username)))
                .check(ViewAssertions.matches(ViewMatchers.withText(expected_username)));

        Espresso.onView((ViewMatchers.withId(R.id.header_email)))
                .check(ViewAssertions.matches(ViewMatchers.withText(expected_email)));
    }

    */
    @Test
    public void canOpenProfile() {
        //Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open(Gravity.LEFT));
        DrawerActions.open(R.id.drawer_layout);
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).check(ViewAssertions.matches(DrawerMatchers.isOpen()));
        //Here's the difference
        Espresso.onView(ViewMatchers.withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_profile));

        mActivityTestRule.launchActivity(new Intent());
        intended(hasComponent(ProfileActivity.class.getName()));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}
