package com.example.apicartoes.di.cards

import com.example.apicartoes.repository.cards.CardRepository
import com.example.apicartoes.repository.cards.CardRepositoryImpl
import org.koin.dsl.module

val cardRepositoryModule = module {
    factory<CardRepository> { CardRepositoryImpl( get() ) }
}