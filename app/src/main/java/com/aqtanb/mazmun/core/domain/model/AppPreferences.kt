package com.aqtanb.mazmun.core.domain.model

data class AppPreferences(
    val themeConfig: ThemeConfig = ThemeConfig.SYSTEM,
    val useDynamicColor: Boolean = false,
    val isFirstLaunch: Boolean = true
)
