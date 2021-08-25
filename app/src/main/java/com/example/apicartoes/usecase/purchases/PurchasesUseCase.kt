package com.example.apicartoes.usecase.purchases

import com.example.apicartoes.data.model.PurchasesModel

interface PurchasesUseCase {

    suspend fun getPurchaseList(
        onSuccess: (List<PurchasesModel>?) -> Unit,
        onError: (String) -> Unit)
}