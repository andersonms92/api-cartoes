package com.example.apicartoes.di.home

import com.example.apicartoes.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel{
        HomeViewModel(
            statusFireBase = get()
        )
    }
}