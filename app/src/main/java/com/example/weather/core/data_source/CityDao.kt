package com.example.weather.core.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.core.domain.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)

    @Query("SELECT * FROM cities ORDER BY created_at DESC")
    fun getCities(): Flow<List<City>>

    @Query("SELECT * FROM cities WHERE id = :id")
    suspend fun getCityById(id: Int): City?

    @Delete
    suspend fun deleteCity(city: City)
}
