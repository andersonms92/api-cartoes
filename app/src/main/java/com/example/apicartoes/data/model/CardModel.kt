package com.example.apicartoes.data.model

import com.google.gson.annotations.SerializedName

data class CardModel(
    val idCartao: String,
    val loginId: String,
    val name: String,
    @SerializedName("cardnumber") val cardNumber: String,
    val code: String,
    val expirationDate: String
)

