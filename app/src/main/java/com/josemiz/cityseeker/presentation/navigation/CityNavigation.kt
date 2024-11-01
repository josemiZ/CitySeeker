package com.josemiz.cityseeker.presentation.navigation

import kotlinx.serialization.Serializable

object CityNavigation{
    @Serializable
    object CityList

    @Serializable
    data class CityMap(
        val city: String,
        val country: String,
        val long: Double,
        val lat: Double
    )
}
