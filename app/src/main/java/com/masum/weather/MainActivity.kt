package com.masum.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.masum.weather.route.SELECTED_KEY
import com.masum.weather.ui.screen.HomeScreen
import com.masum.weather.ui.screen.SearchScreen
import com.masum.weather.ui.theme.WeatherTheme
import com.masum.network.asset_data.zilla_data.Location
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination =com.masum.weather.route.HomeScreen
                        ) {
                            composable(com.masum.weather.route.HomeScreen){backStackEntry->
                               val data= backStackEntry.savedStateHandle.get<String>(SELECTED_KEY)
                                val location= data?.let { Json.decodeFromString<Location>(it) }
                                HomeScreen(navController = navController, location = location)
                            }

                            composable(com.masum.weather.route.SearchScreen) {
                                SearchScreen(navController = navController)
                            }
                        }

                    }
                }
            }
        }
    }
}


