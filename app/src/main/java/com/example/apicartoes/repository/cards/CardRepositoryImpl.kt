package com.example.apicartoes.repository.cards

import com.example.apicartoes.data.model.CardModel
import com.example.apicartoes.data.model.MockApi
import com.example.apicartoes.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardRepositoryImpl(
    private val api: MockApi
) : CardRepository {
    override suspend fun getDataApiCard(
        onSuccess: (List<CardModel>?) -> Unit,
        onError: (String) -> Unit
    ) {
        val returnApi = api.getDataCartoes()

        returnApi.enqueue(object : Callback<List<CardModel>> {
            override fun onResponse(
                call: Call<List<CardModel>>,
                response: Response<List<CardModel>>
            ) {
                if(response.isSuccessful) {
                    onSuccess.invoke(response.body())
                } else {
                    onError.invoke(Constants.ERROR_MESSAGE)
                }
            }

            override fun onFailure(call: Call<List<CardModel>>, t: Throwable) {
                onError.invoke(t.message.toString())
            }
        })
    }
}