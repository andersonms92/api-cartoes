package com.example.apicartoes.usecase.cards

import com.example.apicartoes.data.model.CardModel
import com.example.apicartoes.repository.cards.CardRepository

class CardUseCaseImpl(
    private val repository: CardRepository
) : CardUseCase {
    override suspend fun getListCards(
        onSuccess: (List<CardModel>?) -> Unit,
        onError: (String) -> Unit
    ) {
        repository.getDataApiCard(onSuccess, onError)
    }

}