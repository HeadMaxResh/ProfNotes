package com.example.profnotes.app

import android.app.Application
import com.example.profnotes.data.database.Database

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Database.init(this)
    }
}