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
                            modifier = Modifier.fillMaxSize()
                        ) { city ->
                            navController.navigate(
                                CityNavigation.CityMap(
                                    city = "${city.city}, ${city.country}",
                                    long = city.longitude,
                                    lat = city.latitude
                                )
                            )
                        }
                    }
                    composable<CityNavigation.CityMap> {
                        val args = it.toRoute<CityNavigation.CityMap>()
                        CityMap(
                            city = args.city,
                            lon = args.long,
                            lat = args.lat,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}