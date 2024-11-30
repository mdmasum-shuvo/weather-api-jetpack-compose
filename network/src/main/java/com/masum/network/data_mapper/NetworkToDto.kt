package com.masum.network.data_mapper

import com.masum.network.data_object_model.WeatherDto
import com.masum.network.remote_data.model.weather.WeatherResponse
import com.masum.network.util.WeatherUtils


fun WeatherResponse.toDto(): WeatherDto {

    return WeatherDto(
        temp = WeatherUtils.kelvinToCelsius(main?.temp ?: 0.0),
        feelLike = main?.feelsLike,
        description = weather?.get(0)?.description,
        icon = WeatherUtils.getIconUrl(weather?.get(0)?.icon ?: ""),
    )
}