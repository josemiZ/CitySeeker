package com.josemiz.cityseeker.domain

import com.josemiz.cityseeker.domain.repository.CityRepository
import com.josemiz.cityseeker.domain.usecase.FilterCitiesUseCase
import com.josemiz.cityseeker.domain.usecase.GetCitiesUseCase
import com.josemiz.cityseeker.domain.usecase.SelectFavoriteCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideCityUseCase(
        cityRepository: CityRepository,
    ): GetCitiesUseCase = GetCitiesUseCase(cityRepository)

    @Provides
    fun provideFilterCityUseCase(
        cityRepository: CityRepository,
    ): FilterCitiesUseCase = FilterCitiesUseCase(cityRepository)

    @Provides
    fun provideSelectFavoriteCityUseCase(
        cityRepository: CityRepository,
    ): SelectFavoriteCityUseCase = SelectFavoriteCityUseCase(cityRepository)
}