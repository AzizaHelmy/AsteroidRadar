package com.aziza.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aziza.asteroidradar.data.source.local.AsteroidDataBase
import com.aziza.asteroidradar.data.source.repo.AsteroidRepo

class MainViewModelFactory(private val dataBase: AsteroidDataBase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataBase) as T
        } else {
            throw IllegalArgumentException("View Model class Not found")

        }

    }
}