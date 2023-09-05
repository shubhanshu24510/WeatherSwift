package com.shubhans.weatherforcastapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shubhans.weatherforcastapplication.model.Favorite
import com.shubhans.weatherforcastapplication.model.Unit

@Database(entities = [Favorite::class,Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}