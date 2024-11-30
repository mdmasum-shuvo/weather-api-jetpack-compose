package com.masum.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masum.network.data_object_model.WeatherDto
import com.masum.network.remote_data.repository.GetAllCategoryApiUseCase
import com.masum.weather.location.LatAndLong
import kotlinx.coroutines.launch

class HomeViewModel(
    private val categoryUseCase: GetAllCategoryApiUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _categoryList: MutableLiveData<WeatherDto> = MutableLiveData()

    val categoryList: LiveData<WeatherDto>
        get() = _categoryList

    private var _selectedLocation: MutableLiveData<LatAndLong> = MutableLiveData()

    private val selectedLocation: LiveData<LatAndLong>
        get() = _selectedLocation

    private var _selectedLocationName: MutableLiveData<String> = MutableLiveData()

    val selectedLocationName: LiveData<String>
        get() = _selectedLocationName


    private var isBackFromSearch = true

    fun fetchAllCategory(lat: Double, lng: Double) {
        _selectedLocation.value = LatAndLong(lat, lng)
        viewModelScope.launch {
            val categoryUseCase = categoryUseCase(
                selectedLocation.value?.latitude!!,
                selectedLocation.value?.longitude!!
            )

            categoryUseCase.onSuccess {
                _categoryList.value = it
                isBackFromSearch = false
            }


            categoryUseCase.onFailure {
                Log.e("", "")

            }
        }
    }

    fun setLocationName(locationName: String) {
        _selectedLocationName.value = locationName
    }


}