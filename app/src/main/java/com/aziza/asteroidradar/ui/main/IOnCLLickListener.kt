package com.aziza.asteroidradar.ui.main

import com.aziza.asteroidradar.model.Asteroid

interface IOnCLLickListener {

    fun onItemClicked(asteroid: Asteroid)
}