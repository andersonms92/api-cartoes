package com.example.apicartoes.di.cards

import com.example.apicartoes.ui.cards.CardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cardViewModelModule = module{
    viewModel{
        CardViewModel(
            useCase = get(),
            mainDispatcher = get(),
            ioDispatcher = get()
        )
    }
}