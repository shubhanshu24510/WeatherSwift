package com.shubhans.weatherforcastapplication.screens.main

import androidx.lifecycle.ViewModel
import com.shubhans.weatherforcastapplication.data.DataOrException
import com.shubhans.weatherforcastapplication.model.Weather
import com.shubhans.weatherforcastapplication.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    suspend fun getWeatherData(city: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }
}