package com.masum.network.remote_data.api_use_case

import com.masum.network.data_object_model.WeatherDto
import com.masum.network.remote_data.repository.NetworkDataRepository

interface WeatherApiUseCase {
    suspend operator fun invoke(lat:Double,lng:Double): Result<WeatherDto>
}


class WeatherApiUseCaseImpl(private val repository: NetworkDataRepository) :
    WeatherApiUseCase {

    override suspend fun invoke(lat:Double,lng:Double): Result<WeatherDto> {
        return repository.getWeatherData(lat,lng)
    }

}