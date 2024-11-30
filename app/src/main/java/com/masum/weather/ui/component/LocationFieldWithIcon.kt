package com.masum.weather.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.masum.weather.R
import com.masum.weather.location.FindCurrentLocation
import com.masum.weather.location.LatAndLong
import com.masum.weather.ui.theme.Purple40
import com.masum.weather.ui.theme.light_gray
import com.masum.weather.ui.theme.text_gray
import com.masum.weather.ui.theme.white_color



@Composable
fun LocationFieldWithIcon(
    title: String,
    latitude: MutableState<String>,
    longitude: MutableState<String>,
    color: Color = Color.Black,
    placeholder: String = "Latitude, Longitude",
    isBorderEnable: Boolean = true,
    onLocationSelected: (lat:Double,lng:Double, Boolean) -> Unit
) {


    val isLocationClicked = remember { mutableStateOf(true) }
    val isLocationButtonClicked = remember { mutableStateOf(false) }
    val currentLatLon = remember { mutableStateOf(LatAndLong()) }

    if (isLocationClicked.value) {
        FindCurrentLocation(currentLatLng1 = currentLatLon)
    }

    LaunchedEffect(key1 = latitude.value, key2 = longitude.value) {
        // checking if location value is changed
        if (currentLatLon.value.latitude != latitude.value.toDoubleOrNull() || currentLatLon.value.longitude != longitude.value.toDoubleOrNull()) {
            isLocationClicked.value = false
        }
    }

    LaunchedEffect(key1 = currentLatLon.value) {
        if (currentLatLon.value.latitude != 0.0 && currentLatLon.value.longitude != 0.0) {
            latitude.value = currentLatLon.value.latitude.toString()
            longitude.value = currentLatLon.value.longitude.toString()
            onLocationSelected(
              latitude.value.toDouble(), longitude.value.toDouble(),
                isLocationButtonClicked.value
            )
            // update camera position of marker

        }

    }

    Column {
        Row {
            TextView16_W600(value = title, color = white_color)
        }
        Spacer4DPH()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.height(if (isBorderEnable) 42.dp else 30.dp)
                .wrapContentHeight()
                .clip(if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape)
                .border(
                    width = if (isBorderEnable) 1.dp else 0.dp,
                    color = if (isBorderEnable) light_gray else Color.Unspecified,
                    shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                )
                .background(
                    color = if (isBorderEnable) Color.White else Color.Unspecified,
                    shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                )
                .padding(
                    horizontal = if (isBorderEnable) 12.dp else 0.dp,
                    vertical = if (isBorderEnable) 10.dp else 0.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextView16_W400(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = if (placeholder.isNotEmpty() && latitude.value == "" && longitude.value == "") placeholder else "${latitude.value}, ${longitude.value}",
                color = if (placeholder.isNotEmpty() && latitude.value == "" && longitude.value == "") text_gray else color
            )

            Icon(painterResource(id = R.drawable.my_location),
                "contentDescription",
                tint = Purple40,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        isLocationClicked.value = true
                        isLocationButtonClicked.value = true
                    })
        }
    }
}

