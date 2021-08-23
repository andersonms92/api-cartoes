package com.example.apicartoes.data.model

import com.google.gson.annotations.SerializedName

data class CardModel(
    val id:String,
    val username: String,
    val name:String,
    val password: String,
    @SerializedName("cardnumber") val cardNumber: String,
    @SerializedName("code") val code: String,
    val expirationDate: String
)

