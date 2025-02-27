package com.example.weather.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cities",
    indices = [
        Index(
            value = ["name"],
            unique = true
        )
    ]
)
data class City(
    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo(
        name = "place_id"
    )
    val placeId: String,

    val name: String,

    val latitude: Double,

    val longitude: Double,

    @ColumnInfo(
        name = "created_at"
    )
    val createdAt: Long = System.currentTimeMillis(),
)
