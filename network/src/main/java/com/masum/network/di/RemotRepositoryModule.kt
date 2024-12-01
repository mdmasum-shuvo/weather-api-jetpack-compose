package com.masum.network.di

import com.masum.network.remote_data.api_use_case.WeatherApiUseCase
import com.masum.network.remote_data.api_use_case.WeatherApiUseCaseImpl
import com.masum.network.remote_data.repository.NetworkDataRepository
import com.masum.network.remote_data.repository.RemoteRepositoryImpl
import org.koin.dsl.module

val remoteRepositoryModule = module {
    single<NetworkDataRepository> { RemoteRepositoryImpl(get(), get()) }
    single<WeatherApiUseCase> { WeatherApiUseCaseImpl(get()) }

}