package com.josemiz.cityseeker.data.remote

import com.google.gson.annotations.SerializedName

data class CityEntity(
    @SerializedName("country") val country: String,
    @SerializedName("name") val name: String,
    @SerializedName("_id") val id: Int,
    @SerializedName("coord") val coordinates: CoordinateEntity,
)

data class CoordinateEntity(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double
)