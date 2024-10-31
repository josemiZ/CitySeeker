package com.josemiz.cityseeker.domain

import com.josemiz.cityseeker.data.repository.CityRepositoryImpl
import com.josemiz.cityseeker.domain.repository.CityRepository
import com.josemiz.cityseeker.domain.usecase.GetCitiesUseCase
import com.josemiz.cityseeker.presentation.IoDispatcher
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCityRepository(
        cityRepositoryImpl: CityRepositoryImpl
    ): CityRepository
}

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideCityUseCase(
        cityRepository: CityRepository,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    ): GetCitiesUseCase {
        return GetCitiesUseCase(cityRepository, coroutineDispatcher)
    }
}