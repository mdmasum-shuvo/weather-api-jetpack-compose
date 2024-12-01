package com.masum.weather.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.masum.weather.R
import com.masum.weather.route.SELECTED_KEY
import com.masum.weather.ui.component.Spacer16DPH
import com.masum.weather.ui.component.Spacer8DPH
import com.masum.weather.ui.component.TextFieldWithBorder
import com.masum.weather.ui.component.ZilaItem
import com.masum.weather.ui.theme.light_gray
import com.masum.weather.viewmodel.SearchViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(searchViewModel: SearchViewModel = koinViewModel(), navController: NavController) {
    val context = LocalContext.current

    Surface(color = light_gray) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()) {
            TextFieldWithBorder(
                placeholder = context.getString(R.string.search),
                onValueChanged = { value ->
                    searchViewModel.filterLocations(value)
                })
            Spacer16DPH()

            searchViewModel.filteredLocationList.observeAsState().value?.let { dataList ->
                LazyColumn {
                    items(
                        dataList,
                        key = { item -> item.id ?: 0 }) { item ->
                        ZilaItem(item.name) {
                            navController.popBackStack()
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                SELECTED_KEY,
                                Json.encodeToString(item)
                            )
                        }
                        Spacer8DPH()
                    }
                }
            }

        }
    }
}
