package com.aziza.asteroidradar.data.source.remote

import com.aziza.asteroidradar.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitFactory {
    private val moshi by lazy{
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
    private val retrofit by lazy {

        val okHttpClient = OkHttpClient.Builder()
            .build()
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    private val networkService: NetworkService by lazy {
        retrofit.create(NetworkService::class.java)
    }

    fun getAsteroidApi(): NetworkService {
        return networkService
    }
}


