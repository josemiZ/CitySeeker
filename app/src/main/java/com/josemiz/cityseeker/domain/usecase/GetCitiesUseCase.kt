package com.josemiz.cityseeker.domain.usecase

import com.josemiz.cityseeker.domain.model.CityModel
import com.josemiz.cityseeker.domain.repository.CityRepository
import com.josemiz.cityseeker.presentation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() : List<CityModel> = withContext(dispatcher){
        return@withContext cityRepository.getCities()
    }

}