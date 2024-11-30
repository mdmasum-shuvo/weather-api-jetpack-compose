package com.masum.network.remote_data.repository

import com.masum.network.data_object_model.WeatherDto

interface NetworkDataRepository {

    suspend fun getAllCategory(lat:Double,lnf:Double):Result<WeatherDto>

}