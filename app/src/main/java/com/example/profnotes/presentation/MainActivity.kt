package com.example.profnotes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.profnotes.R
import com.example.profnotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
    }
}