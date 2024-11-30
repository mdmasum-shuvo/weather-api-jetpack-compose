package com.masum.weather.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masum.network.asset_data.zilla_data.Location
import com.masum.network.util.WeatherUtils
import kotlinx.serialization.json.Json

class SearchViewModel (context:Context) : ViewModel() {

    private val _locationList: MutableLiveData<List<Location>> = MutableLiveData(emptyList())

    private val _filteredLocationList: MutableLiveData<List<Location>> = MutableLiveData(emptyList())
    val filteredLocationList: LiveData<List<Location>> get() = _filteredLocationList

    init {
        val json = WeatherUtils.loadJsonFromAssets(context, "Zila.json")
        setLocationData(Json.decodeFromString<List<Location>>(json))
    }

    private fun setLocationData(list: List<Location>) {
        _locationList.value = list
        _filteredLocationList.value = list // Initialize filtered list
    }

    fun filterLocations(query: String) {
        val currentList = _locationList.value.orEmpty()
        _filteredLocationList.value = if (query.isBlank()) {
            currentList // Show all if query is blank
        } else {
            currentList.filter { it.name!!.contains(query, ignoreCase = true) }
        }
    }


}