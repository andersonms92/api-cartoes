package com.example.apicartoes.di.retrofit

import com.example.apicartoes.utils.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module{
    single {

        val client = OkHttpClient().newBuilder().build()

        Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}