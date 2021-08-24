package com.example.apicartoes.repository.login

import com.example.apicartoes.data.model.login.FirebaseModel
import com.example.apicartoes.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginRepositoryImpl : LoginRepository {
    override suspend fun loginFireBase(
        login: FirebaseModel,
        callbackSuccess: () -> Unit,
        callbackError: (error: String) -> Unit
    ) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(login.userName, login.password)
            .addOnCompleteListener {
                when {
                    it.isSuccessful -> {
                        callbackSuccess.invoke()
                    }
                    else -> {
                        callbackError.invoke(Constants.ERROR_MESSAGE)
                    }
                }
//                }.addOnFailureListener {
//                    when (it) {
//                        is FirebaseAuthWeakPasswordException -> {
//                            callbackError.invoke(Constants.WRONG_PASSWORD)
//                        }
//                        else -> {
//                            callbackError.invoke(Constants.ERROR_LOGIN)
//                        }
//                    }
//                }
            }
    }
}