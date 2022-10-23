package com.aziza.asteroidradar.data.source.remote

import com.aziza.asteroidradar.model.Asteroid
import com.aziza.asteroidradar.model.PictureOfDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("neo/rest/v1/feed")
    suspend fun getAllAsteroid(@Query("api_key") apiKey:String):String
    @GET("planetary/apod")
    suspend fun getPictureOfDay(@Query("api_key") apiKey:String):PictureOfDay

}