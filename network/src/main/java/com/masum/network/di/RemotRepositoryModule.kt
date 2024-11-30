package com.masum.network.di

import com.masum.network.remote_data.repository.GetAllCategoryApiUseCase
import com.masum.network.remote_data.repository.GetAllCategoryApiUseCaseImpl
import com.masum.network.remote_data.repository.NetworkDataRepository
import com.masum.network.remote_data.repository.RemoteRepositoryImpl
import org.koin.dsl.module

val remoteRepositoryModule = module {
    single<NetworkDataRepository> { RemoteRepositoryImpl(get(), get()) }
    single<GetAllCategoryApiUseCase> { GetAllCategoryApiUseCaseImpl(get()) }

}