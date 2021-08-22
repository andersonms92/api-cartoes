package com.example.apicartoes.utils

import android.content.Context
import com.example.apicartoes.data.model.login.FirebaseModel

object LoginFieldVerification {

    fun verifyField(email: String, password: String, context: Context) : FirebaseModel {
        return if (email.isNotEmpty() && password.isNotEmpty()) {
            FirebaseModel(email, password, "")
        } else {
            FirebaseModel("", "", "Os campos devem ser preenchidos")
        }
    }

}