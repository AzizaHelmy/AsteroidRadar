package com.aziza.asteroidradar.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aziza.asteroidradar.data.source.local.AsteroidDataBase
import com.aziza.asteroidradar.data.source.repo.AsteroidRepo

class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val dataBase: AsteroidDataBase = AsteroidDataBase.getInstance(context)
            val repo = AsteroidRepo(dataBase)
            return MainViewModel(repo) as T
        } else {
            throw IllegalArgumentException("View Model class Not found")

        }

    }
}