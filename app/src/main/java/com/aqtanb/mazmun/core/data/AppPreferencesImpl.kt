package com.aqtanb.mazmun.core.data

import com.aqtanb.mazmun.core.datastore.AppPreferencesDataSource
import com.aqtanb.mazmun.core.domain.model.AppPreferences
import com.aqtanb.mazmun.core.domain.model.ThemeConfig
import com.aqtanb.mazmun.core.domain.repository.AppPreferencesRepository
import kotlinx.coroutines.flow.StateFlow

class AppPreferencesImpl(
    private val appPreferencesDataSource: AppPreferencesDataSource
) : AppPreferencesRepository {
    override val appPreferences: StateFlow<AppPreferences>
        get() = TODO("Not yet implemented")

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun setFirstLaunchComplemented() {
        TODO("Not yet implemented")
    }

    override suspend fun setThemeConfig(themeConfig: ThemeConfig) {
        TODO("Not yet implemented")
    }
}
