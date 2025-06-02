package com.aqtanb.mazmun.feature.auth.di

import com.aqtanb.mazmun.core.domain.usecase.SignInUseCase
import com.aqtanb.mazmun.feature.auth.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authFeatureModule = module {
    factory { SignInUseCase(get()) }
    viewModel { SignInViewModel(get(), get()) }
}
