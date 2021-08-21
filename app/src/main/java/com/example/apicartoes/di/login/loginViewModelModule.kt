package com.example.apicartoes.di.login

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginViewModelModule = module {
    viewModel{
        LoginViewModel(
            useCase = get(),
            mainDispatcher = get(),
            ioDispatcher = get()
        )
    }
}