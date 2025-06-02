package com.aqtanb.mazmun.app

import android.app.Application
import com.aqtanb.mazmun.core.auth.di.authServiceModule
import com.aqtanb.mazmun.core.data.di.dataModule
import com.aqtanb.mazmun.core.datastore.di.dataStoreModule
import com.aqtanb.mazmun.feature.auth.di.authFeatureModule
import com.aqtanb.mazmun.feature.profile.di.profileModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MazmunApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MazmunApplication)
            modules(
                listOf(
                    dataModule,
                    authFeatureModule,
                    profileModule,
                    dataStoreModule,
                    authServiceModule,
                ),
            )
        }
    }
}
