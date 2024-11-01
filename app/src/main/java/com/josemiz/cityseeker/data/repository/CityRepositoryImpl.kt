package com.josemiz.cityseeker.data.repository

import com.josemiz.cityseeker.data.local.City
import com.josemiz.cityseeker.data.local.CityDao
import com.josemiz.cityseeker.data.remote.CityService
import com.josemiz.cityseeker.domain.model.CityModel
import com.josemiz.cityseeker.domain.model.CoordinatesModel
import com.josemiz.cityseeker.domain.repository.CityRepository
import com.josemiz.cityseeker.presentation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityService: CityService,
    private val cityDao: CityDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : CityRepository {
    override suspend fun getCities(): List<CityModel> = withContext(dispatcher) {
        if (cityDao.getCities().isEmpty()) {
            cityDao.insertAll(
                *cityService.getCities().map {
                    City(
                        it.id,
                        it.name,
                        it.country,
                        it.coordinates.longitude,
                        it.coordinates.latitude,
                        false
                    )
                }.toTypedArray()
            )
        }
        return@withContext cityDao.getCities().map {
            CityModel(
                it.country,
                it.city,
                CoordinatesModel(it.longitude, it.latitude)
            )
        }
    }

}
