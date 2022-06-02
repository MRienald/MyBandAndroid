package com.mrienaldi.myband.core.di

import com.mrienaldi.myband.core.data.source.local.LocalDataSource
import com.mrienaldi.myband.core.data.source.remote.RemoteDataSource
import com.mrienaldi.myband.core.data.source.remote.network.ApiConfig
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.provideApiService }

    single { RemoteDataSource(get()) }

    single { LocalDataSource() }
}