package com.aqtanb.mazmun.core.data

import com.aqtanb.mazmun.core.datastore.AppPreferencesDataSource
import com.aqtanb.mazmun.core.domain.model.AppPreferences
import com.aqtanb.mazmun.core.domain.model.ThemeConfig
import com.aqtanb.mazmun.core.domain.repository.AppPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppPreferencesRepositoryImpl(
    private val appPreferencesDataSource: AppPreferencesDataSource
) : AppPreferencesRepository {
    private val _appPreferences = MutableStateFlow(AppPreferences())
    override val appPreferences: StateFlow<AppPreferences> = _appPreferences.asStateFlow()

    init {
        // If adding nested launches later, use SupervisorJob
        CoroutineScope(Dispatchers.IO).launch {
            appPreferencesDataSource.appPreferences.collect { prefs ->
                _appPreferences.value = prefs
            }
        }
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        return appPreferencesDataSource.setDynamicColorPreference(useDynamicColor)
    }

    override suspend fun setFirstLaunchCompleted() {
        return appPreferencesDataSource.setFirstLaunchCompleted()
    }

    override suspend fun setThemeConfig(themeConfig: ThemeConfig) {
        return appPreferencesDataSource.setThemeConfig(themeConfig)
    }
}
