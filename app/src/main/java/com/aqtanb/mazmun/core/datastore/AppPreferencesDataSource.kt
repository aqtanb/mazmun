package com.aqtanb.mazmun.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.aqtanb.mazmun.core.domain.model.AppPreferences
import com.aqtanb.mazmun.core.domain.model.ThemeConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class AppPreferencesDataSource(
    private val dataStore: DataStore<Preferences>
) {
    val appPreferences: Flow<com.aqtanb.mazmun.core.domain.model.AppPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            AppPreferences(
                themeConfig = ThemeConfig.valueOf(
                    preferences[THEME_CONFIG] ?: ThemeConfig.SYSTEM.name,
                ),
                useDynamicColor = preferences[USE_DYNAMIC_COLOR] ?: false,
                isFirstLaunch = preferences[IS_FIRST_LAUNCH] ?: true,
            )
        }

    suspend fun setThemeConfig(themeConfig: ThemeConfig) {
        dataStore.edit { preferences ->
            preferences[THEME_CONFIG] = themeConfig.name
        }
    }

    suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        dataStore.edit { preferences ->
            preferences[USE_DYNAMIC_COLOR] = useDynamicColor
        }
    }

    suspend fun setFirstLaunchCompleted() {
        dataStore.edit { preferences ->
            preferences[IS_FIRST_LAUNCH] = false
        }
    }

    companion object {
        private val THEME_CONFIG = stringPreferencesKey("theme_config")
        private val USE_DYNAMIC_COLOR = booleanPreferencesKey("use_dynamic_color")
        private val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
    }
}
