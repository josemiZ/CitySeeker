package com.josemiz.cityseeker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josemiz.cityseeker.domain.model.CityModel
import com.josemiz.cityseeker.domain.model.CoordinatesModel
import com.josemiz.cityseeker.domain.usecase.FilterCitiesUseCase
import com.josemiz.cityseeker.domain.usecase.GetCitiesUseCase
import com.josemiz.cityseeker.domain.usecase.SelectFavoriteCityUseCase
import com.josemiz.cityseeker.presentation.model.Cities
import com.josemiz.cityseeker.presentation.model.City
import com.josemiz.cityseeker.presentation.model.CityUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetCitiesUseCase,
    private val filterUseCase: FilterCitiesUseCase,
    private val favoriteCityUseCase: SelectFavoriteCityUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun getCities() {
        fetchJob {
            val list = useCase.invoke()
            _uiState.update {
                it.copy(cities = transformToCities(list))
            }
        }
    }

    private fun transformToCities(list: List<CityModel>) = Cities(list.map(::transformToCity))

    private fun transformToCity(city: CityModel) = City(
        id = city.id,
        city = city.city,
        country = city.country,
        isFavorite = city.isFavorite,
        longitude = city.coordinate.lon,
        latitude = city.coordinate.lat,
    )

    fun filterCities(filter: String) {
        fetchJob {
            val list = filterUseCase.invoke(filter)
            _uiState.update {
                it.copy(cities = transformToCities(list))
            }
        }
    }

    fun onFavoriteClicked(cityClicked: City) {
        fetchJob {
            val city = favoriteCityUseCase.invoke(
                CityModel(
                    country = cityClicked.country,
                    city = cityClicked.city,
                    id = cityClicked.id,
                    isFavorite = cityClicked.isFavorite,
                    CoordinatesModel(cityClicked.longitude, cityClicked.latitude)
                )
            )
            _uiState.update {
                it.copy(cities = it.cities.updateMappedCity(transformToCity(city)))
            }
        }
    }

    private fun Cities.updateMappedCity(city: City): Cities = copy(cities = cities.map {
        if (it.id == city.id) {
            it.copy(isFavorite = city.isFavorite)
        } else {
            it
        }
    })

    private fun fetchJob(job: suspend () -> Unit) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                job.invoke()
            } catch (io: IOException) {
                _uiState.update {
                    it.copy(errorMessage = io.message)
                }
            }
        }
    }
}