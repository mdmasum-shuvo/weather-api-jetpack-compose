package com.masum.network.remote_data.model.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Wind(
    @SerialName("deg") val deg: Int?, @SerialName("speed") val speed: Double?
)