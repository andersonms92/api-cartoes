package com.example.apicartoes.repository.login

import com.example.apicartoes.data.model.login.FirebaseModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginRepositoryImpl : LoginRepository {
    override suspend fun loginFireBase(
        dataLogin: FirebaseModel,
        callbackSuccess: () -> Unit,
        callbackError: (error: String) -> Unit
    ) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(dataLogin.userName, dataLogin.password)
            .addOnCompleteListener {
                when {
                    it.isSuccessful -> {
                        callbackSuccess.invoke()
                    } else -> {
                        callbackError.invoke("Error")
                    }
                }
            }.addOnFailureListener {
                when (it) {
                    is FirebaseAuthWeakPasswordException -> {
                        callbackError.invoke("Senha incorreta!")
                    }
                    else -> {
                        callbackError.invoke("Ocorreu um erro ao efetuar o login")
                    }
                }
            }
    }
}