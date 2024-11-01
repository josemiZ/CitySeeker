package com.josemiz.cityseeker.domain.usecase

import com.josemiz.cityseeker.domain.model.CityModel
import com.josemiz.cityseeker.domain.repository.CityRepository
import javax.inject.Inject

class FilterCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository,
) {

    suspend operator fun invoke(filter: String): List<CityModel> {
        val list = cityRepository.getCities().filter { it.city.startsWith(filter) }
        return list
    }
}