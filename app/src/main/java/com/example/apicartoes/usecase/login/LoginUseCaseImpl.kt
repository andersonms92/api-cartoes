package com.example.apicartoes.usecase.login

import com.example.apicartoes.data.model.login.FirebaseModel
import com.example.apicartoes.repository.login.LoginRepository

class LoginUseCaseImpl(
    private val repository: LoginRepository
) : LoginUseCase {
    override suspend fun executeLoginFireBase(
        dataLogin: FirebaseModel,
        callbackSuccess: () -> Unit,
        callbackError: (error: String) -> Unit
    ) {
        repository.loginFireBase(dataLogin, callbackSuccess, callbackError)
    }

}