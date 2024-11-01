package com.josemiz.cityseeker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.josemiz.cityseeker.presentation.model.City
import com.josemiz.cityseeker.presentation.navigation.CityNavigation
import com.josemiz.cityseeker.presentation.ui.CityMap
import com.josemiz.cityseeker.presentation.ui.CitySeeker
import com.josemiz.cityseeker.presentation.ui.theme.CitySeekerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CitySeekerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = CityNavigation.CityList
                ) {
                    composable<CityNavigation.CityList> {
                        LaunchedEffect(Unit) {
                            viewModel.getCities()
                        }

                        val state by viewModel.uiState.collectAsState()

                        CitySeeker(
                            cities = state.cities,
                            modifier = Modifier.fillMaxSize(),
                            onQueryChange = viewModel::filterCities,
                            onFavoriteClicked = viewModel::onFavoriteClicked
                        ) { city ->
                            navController.navigate(
                                CityNavigation.CityMap(
                                    id = city.id,
                                    city = city.city,
                                    country = city.country,
                                    isFavorite = city.isFavorite,
                                    long = city.longitude,
                                    lat = city.latitude
                                )
                            )
                        }
                    }
                    composable<CityNavigation.CityMap> {
                        val args = it.toRoute<CityNavigation.CityMap>()
                        CityMap(
                            city = City(
                                id = args.id,
                                city = args.city,
                                country = args.country,
                                isFavorite = args.isFavorite,
                                latitude = args.lat,
                                longitude = args.long
                            ),
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}