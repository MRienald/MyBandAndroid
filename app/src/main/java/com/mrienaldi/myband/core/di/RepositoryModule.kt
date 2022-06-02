package com.mrienaldi.myband.core.di

import com.mrienaldi.myband.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
}