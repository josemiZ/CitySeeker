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
        val list = cityDao.getCities()
        if (list.isEmpty()) {
            val cities = cityService.getCities()
            cityDao.insertAll(
                *cities.map {
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
            return@withContext cities.map {
                CityModel(
                    it.country,
                    it.name,
                    it.id,
                    false,
                    CoordinatesModel(it.coordinates.longitude, it.coordinates.latitude)
                )
            }
        }

        return@withContext list.map {
            CityModel(
                it.country,
                it.city,
                it.uid,
                it.isFavorite,
                CoordinatesModel(it.longitude, it.latitude)
            )
        }
    }

    override suspend fun selectFavoriteCity(cityModel: CityModel): CityModel {
        cityDao.updateFavoriteCity(!cityModel.isFavorite, cityModel.id)
        return cityModel.copy(isFavorite = !cityModel.isFavorite)
    }
}
