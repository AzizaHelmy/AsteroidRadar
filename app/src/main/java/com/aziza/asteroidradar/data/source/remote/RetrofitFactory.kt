package com.aziza.asteroidradar.data.source.remote

import com.aziza.asteroidradar.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitFactory {
    private val retrofit by lazy {
        val okHttpClient = OkHttpClient.Builder().build()
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient).build()
    }

    private val networkService: NetworkService by lazy {
        retrofit.create(networkService::class.java)
    }

    fun getAsteroidApi(): NetworkService {
        return networkService
    }
}


