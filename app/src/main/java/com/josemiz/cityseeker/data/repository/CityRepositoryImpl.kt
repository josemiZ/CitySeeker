package com.josemiz.cityseeker.data.repository

import com.josemiz.cityseeker.data.remote.CityService
import com.josemiz.cityseeker.domain.model.CityModel
import com.josemiz.cityseeker.domain.model.CoordinatesModel
import com.josemiz.cityseeker.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityService: CityService,
) : CityRepository {
    override suspend fun getCities(): List<CityModel> {
        return cityService.getCities().map {
            CityModel(
                it.country,
                it.name,
                CoordinatesModel(it.coordinates.longitude, it.coordinates.latitude)
            )
        }
    }

}
