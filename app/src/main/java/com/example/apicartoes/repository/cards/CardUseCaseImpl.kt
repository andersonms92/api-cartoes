package com.example.apicartoes.repository.cards

import com.example.apicartoes.data.model.CardModel

class CardUseCaseImpl(
    val repository: CardRepository
) : CardUseCase {
    override suspend fun getCardList(
        onSuccess: (List<CardModel>?) -> Unit,
        onError: (String) -> Unit
    ) {
        repository.getDataApiCard(onSuccess, onError)
    }
}