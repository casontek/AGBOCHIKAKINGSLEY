package com.chika.decagontest;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import com.chika.decagontest.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainAcivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Before
    public void intentInit(){
        //initializes intent
        Intents.init();
    }

    @Test
    public void checkNewActivity(){
        //click car owners button to call on the Cars owners list activity
        onView(withId(R.id.btn_find_cars)).perform(click());
        //verify that the calling activity received intent and has the correct package name
        intended(allOf(hasComponent(hasShortClassName(".CarOwnersActivity")), toPackage("com.chika.decagontest")));
        //clicks on the button to call on users list activity
        onView(withId(R.id.btn_find_users));
        //verify that the calling activity received intent and has the correct package name
        intended(allOf(hasComponent(hasShortClassName(".Users")), toPackage("com.chika.decagontest")));

    }

}
