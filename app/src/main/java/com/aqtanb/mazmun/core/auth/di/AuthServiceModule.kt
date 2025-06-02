package com.aqtanb.mazmun.core.auth.di

import com.aqtanb.mazmun.core.auth.GoogleAuthService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val authServiceModule = module {
    single { GoogleAuthService(androidContext()) }
}
