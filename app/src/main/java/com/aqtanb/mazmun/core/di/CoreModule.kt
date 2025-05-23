package com.aqtanb.mazmun.core.di

import com.aqtanb.mazmun.core.data.auth.AuthRepositoryImpl
import com.aqtanb.mazmun.core.data.auth.GoogleAuthService
import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { GoogleAuthService(androidContext()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}
