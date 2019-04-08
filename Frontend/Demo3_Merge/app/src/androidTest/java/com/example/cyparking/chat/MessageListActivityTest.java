package com.example.cyparking;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.TextView;

import com.example.cyparking.chat.ChatUser;
import com.example.cyparking.chat.MessageListActivity;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
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
public class MessageListActivityTest {

    @Rule
    public ActivityTestRule<MessageListActivity> mActivityTestRule = new ActivityTestRule<>(MessageListActivity.class);

    private String testToken = "e421ab62-6648-455d-ae71-b51f7574f9ae";
    private String username="test10";

    @Before
    public void setup() throws Exception {
        Intents.init();
    }

    @Test
    public void websocket_success() {
        //Make sure "Chat Connected" toast appears
        Espresso.onView(ViewMatchers.withText("Chat Connected")).inRoot(
                RootMatchers.withDecorView(Matchers.not(Matchers.is(
                        mActivityTestRule.getActivity().getWindow().getDecorView()))))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}
