package com.aziza.asteroidradar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aziza.asteroidradar.data.source.repo.AsteroidRepo
import com.aziza.asteroidradar.model.Asteroid
import com.aziza.asteroidradar.model.PictureOfDay
import com.aziza.asteroidradar.util.AsteroidFilter
import kotlinx.coroutines.launch

class MainViewModel(val repo: AsteroidRepo) : ViewModel() {

    var asteroidResult: LiveData<List<Asteroid>> = MutableLiveData(emptyList())

    private val _pictureResult = MutableLiveData<PictureOfDay>()
    val pictureResult: LiveData<PictureOfDay> = _pictureResult

    private val _asteroidFilter = MutableLiveData<AsteroidFilter>()
    val asteroidFilter: LiveData<AsteroidFilter>
        get() = _asteroidFilter


    init {
        viewModelScope.launch {
            _pictureResult.value = repo.getPictureOfTheDay()
            getSavedAsteroid()
        }
    }

    fun getAsteroidONextWeek() = viewModelScope.launch {
        asteroidResult = repo.getAsteroidONextWeek()
        _asteroidFilter.value = AsteroidFilter.WEEK_ASTEROID
    }

    fun getAsteroidOfToday() = viewModelScope.launch {
        asteroidResult = repo.getAsteroidOfToday()
        _asteroidFilter.value = AsteroidFilter.TODAY_ASTEROID
    }

    fun getSavedAsteroid() = viewModelScope.launch {
        asteroidResult = repo.getAllAsteroid()
        _asteroidFilter.value = AsteroidFilter.SAVED_ASTEROID
    }

}
