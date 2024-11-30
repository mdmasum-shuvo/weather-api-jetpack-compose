package com.masum.weather.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.masum.weather.ui.theme.white_color

@Composable
fun ZilaItem(zilaName: String?, onItemClicked: ()-> Unit) {
    Card(
        modifier = Modifier.clickable {
            onItemClicked()
        }
            .fillMaxWidth()
            .wrapContentHeight(), shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = white_color)

    ) {

        TextView16_W600(value = zilaName ?: "---", modifier =Modifier.padding(16.dp))

    }
}