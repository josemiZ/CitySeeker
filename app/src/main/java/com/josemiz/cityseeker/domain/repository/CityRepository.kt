package com.josemiz.cityseeker.domain.repository

import com.josemiz.cityseeker.domain.model.CityModel

interface CityRepository {
    suspend fun getCities(): List<CityModel>
}