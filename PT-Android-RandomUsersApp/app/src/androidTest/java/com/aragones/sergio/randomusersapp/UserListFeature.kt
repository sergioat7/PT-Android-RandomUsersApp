package com.aragones.sergio.randomusersapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListFeature {

    val mActivityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayScreenTitle() {
        BaristaVisibilityAssertions.assertDisplayed(R.string.contacts)
    }
}