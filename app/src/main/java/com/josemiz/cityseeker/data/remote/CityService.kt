package com.josemiz.cityseeker.data.remote

import retrofit2.http.GET

interface CityService {

    @GET("cities.json")
    suspend fun getCities(): List<CityEntity>
}