package com.example.apicartoes.repository.login

import com.example.apicartoes.data.model.login.FirebaseModel

class LoginUseCaseImpl(
    private val repository: LoginRepository
) : LoginUseCase {

    override suspend fun executeLoginFireBase(
        dataLogin: FirebaseModel,
        callbackSuccess:() -> Unit,
        callbackError: (error: String) -> Unit) {
        repository.loginFireBase(dataLogin, callbackSuccess, callbackError)
    }
    )

}