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
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);

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
    public void register_success() {
        Espresso.onView((ViewMatchers.withId(R.id.usertype)))
                .perform(ViewActions.typeText(new_usertype));

        Espresso.onView((ViewMatchers.withId(R.id.username)))
                .perform(ViewActions.typeText(new_username));

        Espresso.onView((ViewMatchers.withId(R.id.email)))
                .perform(ViewActions.typeText(new_email));

        Espresso.onView((ViewMatchers.withId(R.id.password)))
                .perform(ViewActions.typeText(new_password));

        Espresso.onView(ViewMatchers.withId(R.id.continue_button))
                .perform(ViewActions.click());

        mActivityTestRule.launchActivity(new Intent());
        intended(hasComponent(RegisterActivity.class.getName()));

    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}






