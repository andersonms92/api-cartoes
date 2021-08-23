package com.example.apicartoes.di.login

import com.example.apicartoes.repository.login.LoginRepository
import com.example.apicartoes.repository.login.LoginRepositoryImpl
import org.koin.dsl.module

val loginRepositoryModule = module{
    factory<LoginRepository> { LoginRepositoryImpl() }
}