package com.example.apicartoes.repository.purchases

import com.example.apicartoes.data.model.MockApi
import com.example.apicartoes.data.model.PurchasesModel
import com.example.apicartoes.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PurchasesRepositoryImpl(
    private val api: MockApi
) : PurchasesRepository {
    override suspend fun getDataApiPurchases(
        onSuccess: (List<PurchasesModel>?) -> Unit,
        onError: (String) -> Unit
    ) {
        val returnApi = api.getDataCompras()

        returnApi.enqueue(object : Callback<List<PurchasesModel>> {
            override fun onResponse(
                call: Call<List<PurchasesModel>>,
                response: Response<List<PurchasesModel>>
            ) {
                if(response.isSuccessful) {
                    onSuccess.invoke(response.body())
                } else {
                    onError.invoke(Constants.ERROR_MESSAGE)
                }
            }

            override fun onFailure(call: Call<List<PurchasesModel>>, t: Throwable) {
                onError.invoke(t.message.toString())
            }
        })
    }
}