package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoapp.ui.FormFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, FormFragment())
            .commit()

    }
}