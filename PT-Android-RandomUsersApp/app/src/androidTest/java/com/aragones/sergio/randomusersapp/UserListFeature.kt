package com.aragones.sergio.randomusersapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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