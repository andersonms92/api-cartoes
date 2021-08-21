package com.example.apicartoes.di.login

import org.koin.dsl.module

val loginUseCaseModule = module {
    factory<LoginUseCase> { LoginUseCaseImpl ( get() ) }
}