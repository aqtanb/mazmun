package com.aqtanb.mazmun.app

import android.app.Application
import com.aqtanb.mazmun.core.di.coreModule
import com.aqtanb.mazmun.feature.auth.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MazmunApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MazmunApplication)
            modules(listOf(coreModule, authModule))
        }
    }
}