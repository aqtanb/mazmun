package com.aqtanb.mazmun.core.data.di

import com.aqtanb.mazmun.core.data.AppPreferencesRepositoryImpl
import com.aqtanb.mazmun.core.data.AuthRepositoryImpl
import com.aqtanb.mazmun.core.domain.repository.AppPreferencesRepository
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import org.koin.dsl.module

val dataModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<AppPreferencesRepository> { AppPreferencesRepositoryImpl(get()) }
}
