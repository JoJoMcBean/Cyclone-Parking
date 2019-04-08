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
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
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
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private String username_tobe_typed="test10";
    private String correct_password ="123";
    private String incorrect_password ="1234";

    private String new_username="demo4test";
    private String new_email="demo4test@gmail.com";
    private String new_usertype="default";
    private String new_license="1234";
    private String new_creditCard="12345";
    private String new_password ="123";


    @Before
    public void setup() throws Exception {
        Intents.init();
    }

    @Test
    public void login_success() {
        Log.e("@Test","Performing login success test");
        Espresso.onView((ViewMatchers.withId(R.id.email)))
                .perform(ViewActions.typeText(username_tobe_typed));

        Espresso.onView(ViewMatchers.withId(R.id.password))
                .perform(ViewActions.typeText(correct_password));

        Espresso.onView(ViewMatchers.withId(R.id.login_button))
                .perform(ViewActions.click());

        mActivityTestRule.launchActivity(new Intent());
        //Make sure activity switches to Dashboard
        intended(hasComponent(DashboardActivity.class.getName()));

        //Make sure "Welcome" toast appears
        /*
        Espresso.onView(ViewMatchers.withText("Welcome!")).inRoot(
                RootMatchers.withDecorView(Matchers.not(Matchers.is(
                        mActivityTestRule.getActivity().getWindow().getDecorView()))))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        */

    }

    @Test
    public void login_fail() {
        Log.e("@Test","Performing login success test");
        Espresso.onView((ViewMatchers.withId(R.id.email)))
                .perform(ViewActions.typeText(username_tobe_typed));

        Espresso.onView(ViewMatchers.withId(R.id.password))
                .perform(ViewActions.typeText(incorrect_password));

        Espresso.onView(ViewMatchers.withId(R.id.login_button))
                .perform(ViewActions.click());

        mActivityTestRule.launchActivity(new Intent());
        //Make sure activity switches to Dashboard
        intended(hasComponent(LoginActivity.class.getName()));

    }

    @Test
    public void sign_up_success() {
        Log.e("@Test","Performing login success test");

        Espresso.onView(ViewMatchers.withId(R.id.sign_up_button))
                .perform(ViewActions.click());

        mActivityTestRule.launchActivity(new Intent());
        intended(hasComponent(RegisterActivity.class.getName()));

    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}
