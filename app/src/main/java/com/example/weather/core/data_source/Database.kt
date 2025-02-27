package com.example.weather.core.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.core.domain.model.City

@Database(
    entities = [City::class],
    version = 1,
)
abstract class Database: RoomDatabase() {
    abstract val cityDao: CityDao

    companion object {
        const val DATABASE_NAME = "weather_db"
    }
}
