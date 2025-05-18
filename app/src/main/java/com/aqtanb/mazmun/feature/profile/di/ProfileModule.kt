package com.aqtanb.mazmun.feature.profile.di

import com.aqtanb.mazmun.core.domain.repository.AuthRepository
import com.aqtanb.mazmun.core.domain.usecase.SignOutUseCase
import com.aqtanb.mazmun.feature.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    factory { SignOutUseCase(get<AuthRepository>()) }
    viewModel { ProfileViewModel(get()) }
}
