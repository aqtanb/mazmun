package com.aqtanb.mazmun.core.domain.repository

import com.aqtanb.mazmun.core.domain.model.AppPreferences
import com.aqtanb.mazmun.core.domain.model.ThemeConfig
import kotlinx.coroutines.flow.StateFlow

interface AppPreferencesRepository {
    val appPreferences: StateFlow<AppPreferences>
    suspend fun setThemeConfig(themeConfig: ThemeConfig)
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)
    suspend fun setFirstLaunchCompleted()
}
