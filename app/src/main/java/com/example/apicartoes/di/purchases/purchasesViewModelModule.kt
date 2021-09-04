package com.example.apicartoes.di.purchases

import com.example.apicartoes.ui.purchases.PurchasesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val purchasesViewModelModule = module{
    viewModel{
        PurchasesViewModel(
            useCase = get(),
            mainDispatcher = get(),
            ioDispatcher = get()
        )
    }
}