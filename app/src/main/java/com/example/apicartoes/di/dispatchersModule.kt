package com.example.apicartoes.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatchersModule = module{
    factory { Dispatchers.Main  }
    factory { Dispatchers.IO }

}