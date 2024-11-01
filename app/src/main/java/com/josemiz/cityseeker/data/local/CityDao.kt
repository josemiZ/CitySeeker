package com.josemiz.cityseeker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Query("SELECT * FROM City ORDER BY city ASC")
    suspend fun getCities(): List<City>

    @Insert
    suspend fun insertAll(vararg city: City)

    @Query("UPDATE City SET isFavorite = :isFavorite WHERE uid = :id")
    suspend fun updateFavoriteCity(isFavorite: Boolean, id: Int)
}