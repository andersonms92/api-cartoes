package com.example.apicartoes.di.purchases

import com.example.apicartoes.repository.purchases.PurchasesRepository
import com.example.apicartoes.repository.purchases.PurchasesRepositoryImpl
import org.koin.dsl.module

val purchasesRepositoryModule = module {
    factory<PurchasesRepository> { PurchasesRepositoryImpl( get() ) }
}