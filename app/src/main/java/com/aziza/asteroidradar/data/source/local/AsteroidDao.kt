package com.aziza.asteroidradar.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aziza.asteroidradar.model.Asteroid
import com.aziza.asteroidradar.model.PictureOfDay

@Dao
interface AsteroidDao {
    @Query("select* from Asteroid order by closeApproachDate")
    fun getAllAsteroid(): List<Asteroid>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAsteroid(asteroid: List<Asteroid>): List<Long>

    @Transaction
    fun updateData(asteroid: List<Asteroid>): List<Long> {
        deleteAll()
        return insertAllAsteroid(asteroid)
    }

    @Query("select * from Asteroid where closeApproachDate<=:date order by date(closeApproachDate) Asc")
    fun getAsteroidOfTheDay(date: String): List<Asteroid>

    @Query("Delete from Asteroid")
    fun deleteAll()
    @Query("select * from PictureOfDay")
    fun getPictureOfDay():LiveData<PictureOfDay>
}