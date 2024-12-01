package com.masum.network.remote_data.data_source

import com.masum.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse

class RemoteDataSource(private val client: HttpClient) {

    suspend fun getWeatherData(lat: Double, lng: Double): HttpResponse =
        client.get {
            url(HttpParam.WEATHER)
            parameter(HttpParam.LATITUDE, lat)
            parameter(HttpParam.LONGITUDE, lng)
            parameter(HttpParam.APP_ID, BuildConfig.API_KEY)
        }
}