package com.example.apicartoes.di.login

import com.example.apicartoes.usecase.login.LoginUseCase
import com.example.apicartoes.usecase.login.LoginUseCaseImpl
import org.koin.dsl.module

val loginUseCaseModule = module {
    factory<LoginUseCase> { LoginUseCaseImpl ( get() ) }
}