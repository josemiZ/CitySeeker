package com.josemiz.cityseeker.presentation.model

data class City(
    val id: Int,
    val city: String,
    val country: String,
    val isFavorite: Boolean,
    val latitude: Double,
    val longitude: Double,
) {
    fun getName() : String = "$city, $country"
    fun getCoordinates(): String = "$longitude, $latitude"
    fun getContentDescription() = if (isFavorite) {
        "City is favorite"
    } else {
        "City not favorite"
    }
}