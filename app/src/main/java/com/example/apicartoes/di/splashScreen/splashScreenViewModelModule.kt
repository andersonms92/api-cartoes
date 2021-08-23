package com.example.apicartoes.di.splashScreen

import com.example.apicartoes.ui.splash.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashScreenViewModelModule = module {
    viewModel {
        SplashScreenViewModel(
            statusFireBase = get()
        )
    }
}