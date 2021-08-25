package com.example.apicartoes.data.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MockApi {

    @GET("/api/v1/login")
    fun getDataLogin(): Call<List<LoginModel>>

    @GET("/api/v1/login/1/listacartoes?idCartao")
    fun getDataCartoes(): Call<List<CardModel>>

    @GET("/api/v1/login/1/listacartoes/{idCartao}/compras")
    fun getDataCompras(@Path("idCartao") idCartao: String): Call<List<PurchasesModel>>
}