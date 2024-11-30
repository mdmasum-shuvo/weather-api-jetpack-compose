package com.masum.network.data_object_model

import androidx.compose.runtime.Immutable

@Immutable
data class WeatherDto(
    val temp: Double?,
    val feelLike: Double?,
    val description: String?,
    val icon: String?
)
