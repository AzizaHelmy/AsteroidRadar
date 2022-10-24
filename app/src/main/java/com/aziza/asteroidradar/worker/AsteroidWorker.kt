package com.aziza.asteroidradar.worker

import android.content.Context
import android.util.Log
import androidx.work.*
import com.aziza.asteroidradar.data.source.local.AsteroidDataBase
import com.aziza.asteroidradar.data.source.repo.AsteroidRepo
import com.aziza.asteroidradar.util.Constants
import java.util.concurrent.TimeUnit

class AsteroidWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.e("TAG", "doWork: !!! " )

        val dataBase = AsteroidDataBase.getInstance(applicationContext)
        val repo = AsteroidRepo(dataBase)
        return try {
            repo.refreshAsteroidList()
            Log.e("TAG", "doWork: ${repo.getPictureOfTheDay()} ", )
            Result.success()
        }catch (e:java.lang.Exception){
            Result.failure()
        }

    }
}