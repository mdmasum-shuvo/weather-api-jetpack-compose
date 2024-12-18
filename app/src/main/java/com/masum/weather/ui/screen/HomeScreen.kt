package com.masum.weather.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.masum.network.asset_data.zilla_data.Location
import com.masum.network.data_object_model.WeatherDto
import com.masum.network.util.WeatherUtils
import com.masum.weather.R
import com.masum.weather.ui.component.ImageNormal
import com.masum.weather.ui.component.LocationFieldWithIcon
import com.masum.weather.ui.component.PulseLoading
import com.masum.weather.ui.component.Spacer16DPH
import com.masum.weather.ui.component.TextView16_W400
import com.masum.weather.ui.component.TextView24_W500
import com.masum.weather.ui.component.TextView68_W700
import com.masum.weather.ui.component.gradientColor
import com.masum.weather.ui.theme.white_color
import com.masum.weather.utils.AppUtils.showToast
import com.masum.weather.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    navController: NavController,
    location: Location?
) {
    val context = LocalContext.current
    val latitude = remember {
        mutableStateOf("0")
    }

    val longitude = remember {
        mutableStateOf("0")
    }
    val configuration = LocalConfiguration.current

    val uiState = homeViewModel.uiState.observeAsState().value

    LaunchedEffect(key1 = uiState?.error) {
        if (!uiState?.error.isNullOrBlank()) {
            context.showToast(uiState?.error ?: "")
        }
    }

    if (uiState?.isLoading == true) {
        PulseLoading()
    }
    LaunchedEffect(location) {
        if (location != null && location.coord?.lat != null && location.coord?.lon != null) {
            latitude.value = location.coord?.lat.toString()
            longitude.value = location.coord?.lon.toString()
            homeViewModel.fetchWeatherData(location.coord?.lat!!, location.coord?.lon!!)
            homeViewModel.setLocationName(location.name ?: "")
        }
    }


    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = gradientColor()
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row {
                LocationFieldWithIcon(title = "Selected Location",
                    latitude = latitude,
                    longitude = longitude,
                    onLocationSelected = { lat, lng, isClicked ->
                        if (isClicked || location == null) {
                            homeViewModel.fetchWeatherData(lat, lng)
                            homeViewModel.setLocationName(
                                WeatherUtils.getAddressFromLatLong(
                                    context, latitude = lat, longitude = lng
                                )
                            )
                        }

                    })
            }
            Spacer16DPH()
            homeViewModel.weatherData.observeAsState().value?.let { data ->
                when (configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            WeatherData(homeViewModel, data)

                        }
                    }

                    else -> {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            WeatherData(homeViewModel, data)

                        }

                    }
                }
            }

            Spacer16DPH()

            Button(onClick = {
                navController.navigate(com.masum.weather.route.SearchScreen)
            }) {
                TextView16_W400(
                    value = context.getString(R.string.search_location), color = white_color
                )
            }
        }
    }
}

@Composable
fun WeatherData(homeViewModel: HomeViewModel, data: WeatherDto) {
    Row(
        horizontalArrangement = Arrangement.Center,

        ) {
        Icon(Icons.Default.LocationOn, contentDescription = "Icon", tint = white_color)
        TextView24_W500(
            value = homeViewModel.selectedLocationName.observeAsState().value ?: "",
            color = white_color
        )
    }
    Spacer16DPH()
    ImageNormal(data.icon ?: "")
    Spacer16DPH()
    TextView68_W700(
        value = "${data.temp ?: ""}°C", color = white_color
    )
    TextView24_W500(
        value = data.description ?: "", color = white_color
    )


}

