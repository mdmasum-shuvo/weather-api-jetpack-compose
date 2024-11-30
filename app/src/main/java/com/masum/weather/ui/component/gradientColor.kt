package com.masum.weather.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.masum.weather.ui.theme.Purple40
import com.masum.weather.ui.theme.PurpleGrey40

@Composable
fun gradientColor(): Brush {
    return Brush.horizontalGradient(
        colors = listOf(Purple40, PurpleGrey40)
    )
}