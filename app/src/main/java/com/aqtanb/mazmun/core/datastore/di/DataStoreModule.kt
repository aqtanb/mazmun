package com.aqtanb.mazmun.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.aqtanb.mazmun.core.datastore.AppPreferencesDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_preferences")

val dataStoreModule = module {
    single<DataStore<Preferences>> { androidContext().dataStore }
    single { AppPreferencesDataSource(get()) }
}
