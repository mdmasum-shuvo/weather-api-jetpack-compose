package com.masum.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masum.network.data_object_model.WeatherDto
import com.masum.network.remote_data.api_use_case.WeatherApiUseCase
import com.masum.network.remote_data.repository.NetworkCheckerRepository
import com.masum.network.state.CommonUiState
import com.masum.weather.location.LatAndLong
import kotlinx.coroutines.launch

class HomeViewModel(
    private val weatherApiUseCase: WeatherApiUseCase,
    private val networkCheckerRepository: NetworkCheckerRepository
) : ViewModel() {

    private var _uiState = MutableLiveData(CommonUiState())
    val uiState: LiveData<CommonUiState>
        get() = _uiState
    private var _weatherData: MutableLiveData<WeatherDto> = MutableLiveData()

    val weatherData: LiveData<WeatherDto>
        get() = _weatherData

    private var _selectedLocation: MutableLiveData<LatAndLong> = MutableLiveData()

    private val selectedLocation: LiveData<LatAndLong>
        get() = _selectedLocation

    private var _selectedLocationName: MutableLiveData<String> = MutableLiveData()

    val selectedLocationName: LiveData<String>
        get() = _selectedLocationName


    private var isBackFromSearch = true

    fun fetchWeatherData(lat: Double, lng: Double) {
        if (!networkCheckerRepository.checkNetworkStatus()) {
            _uiState.value = CommonUiState(error = "No Internet Available")
            return
        }

        _uiState.value = CommonUiState(isLoading = true)
        _selectedLocation.value = LatAndLong(lat, lng)
        viewModelScope.launch {
            val weatherApiUseCase = weatherApiUseCase(
                selectedLocation.value?.latitude!!,
                selectedLocation.value?.longitude!!
            )
            weatherApiUseCase.onSuccess {
                _weatherData.value = it
                isBackFromSearch = false
                _uiState.value = CommonUiState(success = true)
            }
            weatherApiUseCase.onFailure {
                _uiState.value = CommonUiState(error = "Something went wrong")
            }
        }
    }

    fun setLocationName(locationName: String) {
        _selectedLocationName.value = locationName
    }


}