package com.example.apicartoes.di.splashScreen

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashScreenViewModelModule = module {
    viewModel {
        SplashScreenViewModel(
            statusUserFireBase = get()
        )
    }
}