package com.aziza.asteroidradar

import android.app.Application
import timber.log.Timber

class AsteroidRadarApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}