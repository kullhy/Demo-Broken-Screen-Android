package com.hidenobi.baseapplication

import android.app.Application
import com.hidenobi.baseapplication.other.PreferenceHelper

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        // init singleton here
        PreferenceHelper.customPreference(this)
    }

    companion object {
        lateinit var instance: MainApplication
    }
}