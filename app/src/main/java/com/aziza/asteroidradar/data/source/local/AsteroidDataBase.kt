package com.aziza.asteroidradar.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aziza.asteroidradar.model.Asteroid
import com.aziza.asteroidradar.model.PictureOfDay

@Database(entities = [Asteroid::class, PictureOfDay::class], version = 1, exportSchema = false)
abstract class AsteroidDataBase : RoomDatabase() {
    abstract fun asteroidDao(): AsteroidDao

    companion object {
        private var instance: AsteroidDataBase? = null

        fun getInstance(context: Context): AsteroidDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AsteroidDataBase::class.java,
                    "asteroid_db"
                ).build()
            }
            return instance!!
        }
    }

}