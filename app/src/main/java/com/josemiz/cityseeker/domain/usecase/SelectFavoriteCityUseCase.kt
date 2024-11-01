package com.josemiz.cityseeker.domain.usecase

import com.josemiz.cityseeker.domain.model.CityModel
import com.josemiz.cityseeker.domain.repository.CityRepository
import javax.inject.Inject

class SelectFavoriteCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    suspend operator fun invoke(cityModel: CityModel): CityModel {
        return cityRepository.selectFavoriteCity(cityModel)
    }
}