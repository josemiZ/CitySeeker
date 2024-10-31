package com.josemiz.cityseeker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

                LaunchedEffect(Unit) {
                    viewModel.getCities()
                }

                val state by viewModel.uiState.collectAsState()

                CitySeeker(cities = state.cities)
            }
        }
    }
}