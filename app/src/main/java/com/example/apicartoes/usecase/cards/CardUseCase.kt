package com.example.apicartoes.usecase.cards

import com.example.apicartoes.data.model.CardModel

interface CardUseCase {

    suspend fun getListCards(
        onSuccess: (List<CardModel>?) -> Unit,
        onError: (String) -> Unit)
}