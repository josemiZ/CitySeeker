package com.josemiz.cityseeker.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun CityMap(
    city: String,
    lon: Double,
    lat: Double,
    modifier: Modifier = Modifier
) {

    val latLng = LatLng(lat, lon)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 15f)
    }
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
    ) {
        Marker(
            state = MarkerState(position = latLng),
            title = city
        )
    }
}