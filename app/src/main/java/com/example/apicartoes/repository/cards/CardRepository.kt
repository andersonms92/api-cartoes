package com.example.apicartoes.repository.cards

import com.example.apicartoes.data.model.CardModel

interface CardRepository {
    suspend fun getDataApiCard(
        onSuccess: (List<CardModel>?) -> Unit,
        onError: (String) -> Unit
    )
}