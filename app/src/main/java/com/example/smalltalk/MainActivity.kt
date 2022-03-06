package com.example.smalltalk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login()

    }

    private fun login() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<LoginFragment>(R.id.fragment_container)
        }
    }
}