package com.example.apicartoes.di.login

import org.koin.dsl.module

val loginRepositoryModule = module{
    factory<LoginRepository> { LoginRepositoryImpl() }
}