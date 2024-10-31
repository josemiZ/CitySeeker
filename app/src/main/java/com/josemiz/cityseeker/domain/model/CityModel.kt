package com.josemiz.cityseeker.domain.model

data class CityModel(
    val country: String,
    val city: String,
    val coordinate: CoordinatesModel
)