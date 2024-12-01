package com.masum.weather.di

import com.masum.weather.viewmodel.HomeViewModel
import com.masum.weather.viewmodel.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(),get()) }
    viewModel { SearchViewModel(androidContext()) }
}