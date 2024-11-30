package com.masum.network.asset_data.zilla_data

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val id: Long?,
    val name: String?,
    val state: String?,
    val country: String?,
    val coord: Coord?
)

@Serializable
data class Coord(
    val lon: Double?,
    val lat: Double?
)