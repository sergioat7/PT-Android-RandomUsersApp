package com.aragones.sergio.randomusersapp

import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import com.aragones.sergio.randomusersapp.base.BaseUiTest
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
}