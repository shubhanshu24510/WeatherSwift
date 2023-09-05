package com.shubhans.weatherforcastapplication.repository

import android.util.Log
import com.shubhans.weatherforcastapplication.data.DataOrException
import com.shubhans.weatherforcastapplication.model.Weather
import com.shubhans.weatherforcastapplication.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi ){
    suspend fun getWeather(cityQuery:String)
    :DataOrException<Weather,Boolean,Exception>{
        val response =try {
            api.getWeather(query = cityQuery)

        }catch (e:Exception){
            return DataOrException(e = e)
        }
        Log.d("INSIDE","get weather:${response}")
        return DataOrException(data = response)
    }
}