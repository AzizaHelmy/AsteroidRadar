package com.aziza.asteroidradar

//import androidx.work.*
//import com.aziza.asteroidradar.worker.AsteroidWorker
import android.app.Application
import androidx.work.*
import com.aziza.asteroidradar.util.Constants
import com.aziza.asteroidradar.worker.AsteroidWorker
import java.util.concurrent.TimeUnit


class AsteroidRadarApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val constraint = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val dailyFetchRequest = PeriodicWorkRequestBuilder<AsteroidWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(Constants.WORKER_NAME, ExistingPeriodicWorkPolicy.KEEP, dailyFetchRequest)
    }


}


