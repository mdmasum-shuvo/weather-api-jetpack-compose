package com.masum.weather.di

import com.masum.network.di.dispatchersModule
import com.masum.network.di.remoteDataSourceModule
import com.masum.network.di.remoteRepositoryModule
import org.koin.dsl.module

val appModule = module {
    includes(remoteDataSourceModule,dispatchersModule, remoteRepositoryModule, viewModelModule)
}