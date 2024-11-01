package com.josemiz.cityseeker.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey val uid: Int,
    @ColumnInfo("city") val city: String,
    @ColumnInfo("country") val country: String,
    @ColumnInfo("longitude") val longitude: Double,
    @ColumnInfo("latitude") val latitude: Double,
    @ColumnInfo("isFavorite") val isFavorite: Boolean,
)