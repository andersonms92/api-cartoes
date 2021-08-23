package com.example.apicartoes.usecase.login

import com.example.apicartoes.data.model.login.FirebaseModel

interface LoginUseCase {

    suspend fun executeLoginFireBase(
        dataLogin: FirebaseModel,
        callbackSuccess:() -> Unit,
        callbackError: (error:String) -> Unit
    )
}