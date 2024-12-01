package com.masum.network.remote_data.repository

import com.masum.network.data_object_model.WeatherDto

interface NetworkDataRepository {

    suspend fun getWeatherData(lat: Double, lnf: Double): Result<WeatherDto>

}