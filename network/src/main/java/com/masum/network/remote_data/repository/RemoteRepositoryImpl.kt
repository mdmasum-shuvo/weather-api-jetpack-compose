package com.masum.network.remote_data.repository

import com.masum.network.data_mapper.toDto
import com.masum.network.data_object_model.WeatherDto
import com.masum.network.remote_data.data_source.RemoteDataSource
import com.masum.network.remote_data.model.weather.WeatherResponse
import io.ktor.client.call.body
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteRepositoryImpl(
    private val remoteDataSource: RemoteDataSource, private val ioDispatcher: CoroutineDispatcher
) : NetworkDataRepository {
    override suspend fun getWeatherData(lat: Double, lng: Double): Result<WeatherDto> =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource.getWeatherData(lat, lng).body<WeatherResponse>().toDto()
            }

        }

}