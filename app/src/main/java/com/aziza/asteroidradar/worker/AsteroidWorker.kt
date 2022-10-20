package com.aziza.asteroidradar.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aziza.asteroidradar.data.source.local.AsteroidDataBase
import com.aziza.asteroidradar.data.source.repo.AsteroidRepo

class AsteroidWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val dataBase = AsteroidDataBase.getInstance(applicationContext)
        val repo = AsteroidRepo(dataBase)
        return try {
            repo.refreshAsteroidList()
            repo.getPictureOfTheDay()
            Result.success()
        }catch (e:java.lang.Exception){
            Result.failure()
        }

    }
}