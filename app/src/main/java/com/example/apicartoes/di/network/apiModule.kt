package com.example.apicartoes.di.network

import com.example.apicartoes.data.model.MockApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    factory { get<Retrofit>().create(MockApi::class.java) }
}