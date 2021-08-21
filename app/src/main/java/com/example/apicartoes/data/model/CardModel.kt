package com.example.apicartoes.data.model

import com.google.gson.annotations.SerializedName

data class CardModel(
    @SerializedName("id") val id:String?,
    @SerializedName("username") val name:String,
    val password: String,
    @SerializedName("cardnumber") val CardNumber:String,
    @SerializedName("code") val code:String,
    @SerializedName("date") val date:String
)