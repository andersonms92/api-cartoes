package com.example.apicartoes.data.model

import retrofit2.Call
import retrofit2.http.GET

interface MockApi {

    @GET("/api/v1/login")
    fun getDataLogin(): Call<List<LoginModel>>

    @GET("/api/v1/login/1/listacartoes?idCartao")
    fun getDataCartoes(): Call<List<CardModel>>

    @GET("/api/v1/login/$/listacartoes/$/compras")
    fun getDataCompras(): Call<List<ComprasModel>>
}