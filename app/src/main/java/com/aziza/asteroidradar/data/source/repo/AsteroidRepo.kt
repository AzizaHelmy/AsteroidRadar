package com.aziza.asteroidradar.data.source.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.aziza.asteroidradar.BuildConfig
import com.aziza.asteroidradar.data.source.local.AsteroidDataBase
import com.aziza.asteroidradar.data.source.remote.RetrofitFactory
import com.aziza.asteroidradar.data.source.remote.parseAsteroidsJsonResult
import com.aziza.asteroidradar.model.Asteroid
import com.aziza.asteroidradar.model.PictureOfDay
import com.aziza.asteroidradar.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepo(private val localDataBase: AsteroidDataBase) {

    suspend fun refreshAsteroidList() {
        withContext(Dispatchers.IO) {
            val asteroid = RetrofitFactory.getAsteroidApi().getAllAsteroid(BuildConfig.API_KEY)
            val json = JSONObject(asteroid)
            val data = parseAsteroidsJsonResult(json)
            localDataBase.asteroidDao().updateData(data)
        }
    }

    suspend fun getPictureOfTheDay(): PictureOfDay? {

        return withContext(Dispatchers.IO) {
            val response =
                RetrofitFactory.getAsteroidApi().getPictureOfDay(BuildConfig.API_KEY)

            return@withContext response
        }
    }

    suspend fun getAllAsteroid(): LiveData<List<Asteroid>> {
        return withContext(Dispatchers.IO) {
            return@withContext localDataBase.asteroidDao().getAllAsteroid()
        }

    }

    fun getAsteroidOfToday(): LiveData<List<Asteroid>> {
        return localDataBase.asteroidDao().getAsteroidOfTheDay(Constants.getCurrentDate())
    }

    fun getAsteroidONextWeek(): LiveData<List<Asteroid>> {
        return localDataBase.asteroidDao().getAsteroidTheNextWeek()
    }


}