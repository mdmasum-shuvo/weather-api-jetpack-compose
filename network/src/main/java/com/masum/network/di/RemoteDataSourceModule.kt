package com.masum.network.di

import com.masum.network.remote_data.data_source.RemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource(KtorHttpClient().get()) }

}
