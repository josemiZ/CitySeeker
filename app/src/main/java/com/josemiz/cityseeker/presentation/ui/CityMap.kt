package com.josemiz.cityseeker.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.josemiz.cityseeker.presentation.model.City

@Composable
fun CityMap(
    city: City?,
    modifier: Modifier = Modifier
) {

    val lat = city?.latitude?:0.0
    val lon = city?.longitude?:0.0
    val latLng = LatLng(lat, lon)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 15f)
    }
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
    ) {
        if (city != null) {
            Marker(
                state = MarkerState(position = latLng),
                title = city.getName()
            )
        }
    }
}