package com.shubhans.weatherforcastapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.shubhans.weatherforcastapplication.model.Favorite
import com.shubhans.weatherforcastapplication.model.Unit
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {
    @Query("SELECT * from fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * from fav_tbl where city =:city")
    suspend fun getFavById(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    //unit table
    @Query("SELECT * from setting_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnits(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnits(unit: Unit)

    @Query("DELETE from setting_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnits(unit: Unit)
}