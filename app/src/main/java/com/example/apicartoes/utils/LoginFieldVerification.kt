package com.example.apicartoes.utils

import android.content.Context
import com.example.apicartoes.data.model.login.FirebaseModel

object LoginFieldVerification {

    fun verifyField(email: String, password: String, context: Context) : FirebaseModel {
        return if (email.isNotEmpty() && password.isNotEmpty()) {
            FirebaseModel(email, password, Constants.EMPTY)
        } else {
            FirebaseModel(Constants.EMPTY, Constants.EMPTY, Constants.FIELDS_MUST_BE_COMPLETED)
        }
    }

}