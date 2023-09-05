package com.shubhans.weatherforcastapplication.repository

import com.shubhans.weatherforcastapplication.data.WeatherDao
import com.shubhans.weatherforcastapplication.model.Favorite
import com.shubhans.weatherforcastapplication.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class weatherDbRepository @Inject constructor(private val weatherDao: WeatherDao){
    fun getFavorites(): Flow<List<Favorite>> =weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) =weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites(favorite: Favorite) =weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) =weatherDao.deleteFavorite(favorite)
    suspend fun getFavById(city:String) =weatherDao.getFavById(city)

    //units
    fun getUnits(): Flow<List<Unit>> =weatherDao.getUnits()
    suspend fun insertUnits(unit: Unit) =weatherDao.insertUnits(unit)
    suspend fun updateUnits(unit: Unit) = weatherDao.updateUnits(unit)
    suspend fun deleteAllUnits() =weatherDao.deleteAllUnits()
    suspend fun deleteUnits(unit: Unit) =weatherDao.deleteUnits(unit)

}

