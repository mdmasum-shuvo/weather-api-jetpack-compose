package com.masum.weather.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Spacer16DPH() {
    Spacer(modifier = Modifier.height(16.dp))
}


@Composable
fun Spacer8DPH() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun Spacer4DPH() {
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun Spacer8DPW() {
    Spacer(modifier = Modifier.width(8.dp))
}
