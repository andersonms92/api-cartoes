package com.example.apicartoes.di.utils

import org.koin.dsl.module

val statusUserFireBaseModule = module {
    factory { StatusUserFireBase(
        get(),
        get()
    ) }
}