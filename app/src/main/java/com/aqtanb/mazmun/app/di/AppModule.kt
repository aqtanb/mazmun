package com.aqtanb.mazmun.app.di

import com.aqtanb.mazmun.app.MazmunAppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MazmunAppViewModel(get()) }
}
