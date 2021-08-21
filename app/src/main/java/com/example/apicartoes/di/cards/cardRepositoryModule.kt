package com.example.apicartoes.di.cards

import org.koin.dsl.module


val cardRepositoryModule = module {
    factory<CardRepository> { CardRepositoryImpl( get() ) }
}