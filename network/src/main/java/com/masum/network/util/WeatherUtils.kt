package com.masum.network.util

import android.content.Context
import android.location.Geocoder
import java.util.Locale

object WeatherUtils {

    fun kelvinToCelsius(temp: Double): Double {
        return (temp - 273.15).toBigDecimal().setScale(2, java.math.RoundingMode.HALF_UP).toDouble()
    }


    fun getIconUrl(icon:String):String{
        return  "https://openweathermap.org/img/wn/${icon}@4x.png"
    }

    fun loadJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    fun getAddressFromLatLong(context: Context, latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        return try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses!!.isNotEmpty()) {
                val address = addresses[0]
                // Format the address as desired
                "${address.subAdminArea}"
            } else {
                "Address not found"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Unable to fetch address"
        }
    }
}