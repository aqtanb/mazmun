package com.aqtanb.mazmun.feature.auth

import com.aqtanb.mazmun.core.domain.usecase.SignInUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { SignInViewModel(authRepo = get(), signInUseCase = get()) }
    factory { SignInUseCase(get()) }
}