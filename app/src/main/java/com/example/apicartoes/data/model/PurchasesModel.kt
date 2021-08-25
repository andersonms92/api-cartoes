package com.example.apicartoes.data.model

data class PurchasesModel(
    val id: String,
    val listacartoesId: String,
    val valor: String,
    val formaPagamento: String,
    val nomeProduto: String,
    val loja: String,
    val descricao: String,
    val dataCompra: String
)
