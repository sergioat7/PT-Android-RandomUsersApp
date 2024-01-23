package com.aragones.sergio.randomusersapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import com.aragones.sergio.randomusersapp.base.BaseUiTest
import org.hamcrest.Matchers
import org.junit.Test

class UserDetailsFeature : BaseUiTest() {

    @Test
    fun displayScreenTitle() {

        navigateToUserDetails(0)

        BaristaVisibilityAssertions.assertDisplayed("Manasa Chatterjee")
    }

    @Test
    fun displaysListOfUsers() {

        navigateToUserDetails(0)

        BaristaVisibilityAssertions.assertDisplayed("Manasa Chatterjee")

        BaristaVisibilityAssertions.assertDisplayed("manasa.chatterjee@example.com")

        BaristaVisibilityAssertions.assertDisplayed("Female")

        BaristaVisibilityAssertions.assertDisplayed("19/01/2007")

        BaristaVisibilityAssertions.assertDisplayed("9485012520")
    }

    private fun navigateToUserDetails(row: Int) {

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.user_image),
                ViewMatchers.isDescendantOfA(
                    nthChildOf(
                        ViewMatchers.withId(R.id.recycler_view_users),
                        row
                    )
                )
            )
        )
            .perform(ViewActions.click())
    }
}