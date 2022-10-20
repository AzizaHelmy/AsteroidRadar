package com.aziza.asteroidradar.ui.main

import androidx.lifecycle.*
import com.aziza.asteroidradar.data.source.local.AsteroidDataBase
import com.aziza.asteroidradar.data.source.repo.AsteroidRepo
import com.aziza.asteroidradar.model.Asteroid
import com.aziza.asteroidradar.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val dataBase: AsteroidDataBase) : ViewModel() {
    private val _asteroidResult = MutableLiveData<List<Asteroid>>()
    val asteroidResult: LiveData<List<Asteroid>> = _asteroidResult
    val asteroidList: MediatorLiveData<List<Asteroid>> = MediatorLiveData()
    val repo: AsteroidRepo = AsteroidRepo(dataBase)
    val pictureOfDay = repo.pictureOfDay
    // val todayAsteroid = repo.asteroidList

    fun addAsteroidsToDB(asteroids: List<Asteroid>) = viewModelScope.launch {
        //repo.addAsteroidsToDB(asteroids)
        // _asteroidResult.postValue()
    }

    fun getAsteroidONextWeek() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            _asteroidResult.postValue(dataBase.asteroidDao().getAllAsteroid())
        }

//        asteroidList.addSource(todayAsteroid) {
//
//        }
    }

    fun getAsteroidOfToday() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            _asteroidResult.postValue(dataBase.asteroidDao().getAsteroidOfTheDay(Constants.getCurrentDate()))

        }
    }

    fun getSavedAsteroid() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            _asteroidResult.postValue(dataBase.asteroidDao().getAllAsteroid())

        }
    }

}
