package com.example.apicartoes.data.model

data class ComprasModel(
    val id: String,
    val listacartoesId: String,
    val valor: String,
    val parcelado: String,
    val loja: String,
    val descricao: String,
    val dataCompra: String
)
