package com.josemiz.cityseeker.presentation.model

data class CityUiState(
    val loading: Boolean = false,
    val cities: Cities = Cities(emptyList()),
    val errorMessage: String? = null
)