package com.example.apicartoes.data.model

import retrofit2.Call
import retrofit2.http.GET

interface MockApi {

    @GET("/api/v1/login")
    fun getDataApi(): Call<List<CardModel>>
}