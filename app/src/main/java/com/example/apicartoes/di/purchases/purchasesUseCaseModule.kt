package com.example.apicartoes.di.purchases

import com.example.apicartoes.usecase.purchases.PurchasesUseCase
import com.example.apicartoes.usecase.purchases.PurchasesUseCaseImpl
import org.koin.dsl.module

val purchasesUseCaseModule = module {
    factory<PurchasesUseCase> { PurchasesUseCaseImpl( get() ) }
}