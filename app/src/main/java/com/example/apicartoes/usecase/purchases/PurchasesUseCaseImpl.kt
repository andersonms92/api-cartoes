package com.example.apicartoes.usecase.purchases

import com.example.apicartoes.data.model.PurchasesModel
import com.example.apicartoes.repository.purchases.PurchasesRepository

class PurchasesUseCaseImpl(
    private val repository: PurchasesRepository
) : PurchasesUseCase {
    override suspend fun getPurchaseList(
        onSuccess: (List<PurchasesModel>?) -> Unit,
        onError: (String) -> Unit
    ) {
        repository.getDataApiPurchases(onSuccess, onError)
    }

}