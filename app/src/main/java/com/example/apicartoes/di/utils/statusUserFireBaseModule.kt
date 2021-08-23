package com.example.apicartoes.di.utils

import com.example.apicartoes.utils.StatusFireBase
import org.koin.dsl.module

val statusUserFireBaseModule = module {
    factory { StatusFireBase(
        get(),
        get()
    ) }
}