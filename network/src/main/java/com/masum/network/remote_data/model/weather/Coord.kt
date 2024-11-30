package com.masum.network.remote_data.model.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Coord(
     @SerialName("lat")
    val lat: Double?,
     @SerialName("lon")
    val lon: Double?
)