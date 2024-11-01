package com.josemiz.cityseeker.domain.model

data class CityModel(
    val country: String,
    val city: String,
    val id: Int,
    val isFavorite: Boolean,
    val coordinate: CoordinatesModel
)