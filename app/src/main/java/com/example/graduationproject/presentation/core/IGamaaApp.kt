package com.example.graduationproject.presentation.core

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class IGamaaApp(): Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}