package com.example.apicartoes.di.home

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeViewModelModule = module {
    viewModel{
        HomeViewModel(
            statusUserFireBase = get()
        )
    }
}