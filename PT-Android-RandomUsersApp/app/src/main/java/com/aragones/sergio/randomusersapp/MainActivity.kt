package com.aragones.sergio.randomusersapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aragones.sergio.randomusersapp.userlist.UserListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, UserListFragment.newInstance())
                .commit()
        }
    }
}