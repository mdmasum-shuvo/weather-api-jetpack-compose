package com.masum.weather.ui.component
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.masum.weather.ui.theme.Purple40
import com.masum.weather.ui.theme.light_gray

@Composable
fun PulseLoading(title: String = "") {

    var context= LocalContext.current
    Dialog(onDismissRequest = {}) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.Center),

                colors = CardDefaults.cardColors(
                    containerColor = light_gray// Light Blue
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {}
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .align(Alignment.Center),
            ) {
                // ImageNormal(modifier = Modifier.size(72.dp))
                CircularProgressIndicator(color = Purple40)
                Spacer16DPH()
                TextView12_W700(value = if (title!="") title else "Please Wait")
            }
        }


    }


}