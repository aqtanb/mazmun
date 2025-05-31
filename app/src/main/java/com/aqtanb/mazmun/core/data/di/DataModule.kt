package com.aqtanb.mazmun.core.data.di

import com.aqtanb.mazmun.core.data.AppPreferencesImpl
import com.aqtanb.mazmun.core.data.AuthRepositoryImpl
import com.aqtanb.mazmun.core.data.GoogleAuthService
import com.aqtanb.mazmun.core.domain.model.AppPreferences
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { GoogleAuthService(androidContext()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<AppPreferences> { AppPreferencesImpl(get()) }
}
