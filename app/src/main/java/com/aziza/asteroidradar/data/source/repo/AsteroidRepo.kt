package com.aziza.asteroidradar.data.source.repo

import androidx.lifecycle.LiveData
import com.aziza.asteroidradar.data.source.remote.parseAsteroidsJsonResult
import com.aziza.asteroidradar.data.source.local.AsteroidDataBase
import com.aziza.asteroidradar.data.source.remote.RetrofitFactory
import com.aziza.asteroidradar.model.Asteroid
import com.aziza.asteroidradar.model.PictureOfDay
import com.aziza.asteroidradar.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepo(private val localDataBase: AsteroidDataBase) {
    var image = ""
    val pictureOfDay:LiveData<PictureOfDay> = localDataBase.asteroidDao().getPictureOfDay()
   // val asteroidList:List<Asteroid> = localDataBase.asteroidDao().getAllAsteroid()

    fun getAllAsteroid(): List<Asteroid> {
        return localDataBase.asteroidDao().getAllAsteroid()
    }

    fun addAsteroidsToDB(asteroids: List<Asteroid>) {
        localDataBase.asteroidDao().insertAllAsteroid(asteroids)
    }

    suspend fun refreshAsteroidList() {
        withContext(Dispatchers.IO) {
            val asteroid = RetrofitFactory.getAsteroidApi().getAllAsteroid(Constants.API_KEY)
            val json = JSONObject(asteroid)
            val data = parseAsteroidsJsonResult(json)
            localDataBase.asteroidDao().updateData(data)
        }
    }

    fun getAsteroidOfToday(): List<Asteroid> {
        return localDataBase.asteroidDao().getAsteroidOfTheDay(Constants.getCurrentDate())
    }

    suspend fun getAsteroidONextWeek(): List<Asteroid> {

         return localDataBase.asteroidDao().getAllAsteroid()

    }

    fun getSavedAsteroid(): List<Asteroid> {
        return localDataBase.asteroidDao().getAllAsteroid()
    }

    suspend fun getPictureOfTheDay() {
        withContext(Dispatchers.IO) {
            val response =
                RetrofitFactory.getAsteroidApi().getPictureOfDay(Constants.API_KEY).body()
            if (response?.mediaType.equals("image")) {
                image = response?.url.toString()
            }
        }
    }

}