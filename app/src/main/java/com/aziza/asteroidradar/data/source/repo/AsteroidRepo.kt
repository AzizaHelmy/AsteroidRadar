package com.aziza.asteroidradar.data.source.repo

import android.util.Log
import androidx.lifecycle.LiveData
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
    val pictureOfDay: LiveData<PictureOfDay> = localDataBase.asteroidDao().getPictureOfDay()

    suspend fun getAllAsteroid(): LiveData<List<Asteroid>> {
        return withContext(Dispatchers.IO) {
            return@withContext localDataBase.asteroidDao().getAllAsteroid()
        }

    }

    suspend fun refreshAsteroidList() {
        withContext(Dispatchers.IO) {
            val asteroid = RetrofitFactory.getAsteroidApi().getAllAsteroid(Constants.API_KEY)
            val json = JSONObject(asteroid)
            val data = parseAsteroidsJsonResult(json)

            localDataBase.asteroidDao().updateData(data)
            Log.e("TAG", "refreshAsteroidList: $data")
        }
    }

    fun getAsteroidOfToday(): LiveData<List<Asteroid>> {
        return localDataBase.asteroidDao().getAsteroidOfTheDay(Constants.getCurrentDate())
    }

     fun getAsteroidONextWeek(): LiveData<List<Asteroid>> {

        return localDataBase.asteroidDao().getAsteroidTheNextWeek()
    }

    fun getSavedAsteroid():LiveData<List<Asteroid>>  {
        return localDataBase.asteroidDao().getAllAsteroid()
    }

    suspend fun getPictureOfTheDay(): PictureOfDay? {
        Log.e("TAG", "image!")

        return withContext(Dispatchers.IO) {
            val response =
                RetrofitFactory.getAsteroidApi().getPictureOfDay(Constants.API_KEY)

            return@withContext response
        }
    }


}