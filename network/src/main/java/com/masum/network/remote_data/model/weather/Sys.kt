package com.masum.network.remote_data.model.weather


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sys(
     @SerialName("country")
    val country: String?,
     @SerialName("id")
    val id: String?,
     @SerialName("sunrise")
    val sunrise: String?,
     @SerialName("sunset")
    val sunset: String?,
     @SerialName("type")
    val type: String?
)