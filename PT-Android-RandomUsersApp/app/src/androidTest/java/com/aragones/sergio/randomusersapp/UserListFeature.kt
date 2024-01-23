package com.aragones.sergio.randomusersapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import com.aragones.sergio.randomusersapp.base.BaseUiTest
import org.hamcrest.Matchers.allOf
import org.junit.Test

class UserListFeature : BaseUiTest() {

    @Test
    fun displayScreenTitle() {
        BaristaVisibilityAssertions.assertDisplayed(R.string.contacts)
    }

    @Test
    fun displaysListOfUsers() {
        BaristaRecyclerViewAssertions.assertRecyclerViewItemCount(R.id.recycler_view_users, 50)

        onView(
            allOf(
                withId(R.id.text_view_name),
                isDescendantOfA(nthChildOf(withId(R.id.recycler_view_users), 0))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("Manasa Chatterjee")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(
            allOf(
                withId(R.id.text_view_email),
                isDescendantOfA(nthChildOf(withId(R.id.recycler_view_users), 0))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("manasa.chatterjee@example.com")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun navigateToDetailsScreen() {

        onView(
            allOf(
                withId(R.id.user_image),
                isDescendantOfA(nthChildOf(withId(R.id.recycler_view_users), 0))
            )
        )
            .perform(click())

        BaristaVisibilityAssertions.assertDisplayed(R.id.constraint_layout_details)
    }
}