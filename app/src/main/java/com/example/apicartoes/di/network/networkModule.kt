package com.example.apicartoes.di.network

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://611ea4fe9771bf001785c5aa.mockapi.io/"

val networkModule = module{
    single {

        val client = OkHttpClient().newBuilder().build()

        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}