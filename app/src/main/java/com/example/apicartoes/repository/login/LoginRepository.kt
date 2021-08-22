package com.example.apicartoes.repository.login

import com.example.apicartoes.data.model.login.FirebaseModel

interface LoginRepository {

    suspend fun loginFireBase(
        dataLogin: FirebaseModel,
        callbackSuccess:() -> Unit,
        callbackError: (error: String) -> Unit)
}