package com.example.apicartoes.di.cards

import org.koin.dsl.module
import com.example.apicartoes.usecase.cards.CardUseCase
import com.example.apicartoes.usecase.cards.CardUseCaseImpl

val cardUseCaseModule = module {
    factory<CardUseCase> { CardUseCaseImpl( get() ) }
}