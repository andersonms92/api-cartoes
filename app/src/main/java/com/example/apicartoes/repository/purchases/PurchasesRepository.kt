package com.example.apicartoes.repository.purchases

import com.example.apicartoes.data.model.PurchasesModel

interface PurchasesRepository {
    suspend fun getDataApiPurchases(
        onSuccess: (List<PurchasesModel>?) -> Unit,
        onError: (String) -> Unit
    )
}