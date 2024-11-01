package com.josemiz.cityseeker.domain.usecase

import com.josemiz.cityseeker.domain.model.CityModel
import com.josemiz.cityseeker.domain.repository.CityRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository,
) {

    suspend operator fun invoke() : List<CityModel> {
        return cityRepository.getCities()
    }

}