package com.josemiz.cityseeker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josemiz.cityseeker.domain.model.CityModel
import com.josemiz.cityseeker.domain.usecase.FilterCitiesUseCase
import com.josemiz.cityseeker.domain.usecase.GetCitiesUseCase
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

    private fun transformToCities(list: List<CityModel>): Cities {
        return Cities(
            list.map {
                City(
                    city = it.city,
                    country = it.country,
                    longitude = it.coordinate.lon,
                    latitude = it.coordinate.lat,
                )
            }
        )
    }

    fun filterCities(filter: String) {
        fetchJob {
            val list = filterUseCase.invoke(filter)
            _uiState.update {
                it.copy(cities = transformToCities(list))
            }
        }
    }

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