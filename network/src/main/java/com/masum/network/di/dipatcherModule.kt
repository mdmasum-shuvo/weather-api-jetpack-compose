package com.masum.network.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val dispatchersModule = module {
    single { providesIODispatcher() }
    single { providesDefaultDispatcher() }
}

fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
