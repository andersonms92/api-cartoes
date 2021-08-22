package com.example.apicartoes.utils

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import java.lang.Exception

class StatusFireBase (
    mainDipatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) {
    private val supervisorJob = SupervisorJob()
    private val mainDispatcher = CoroutineScope(mainDipatcher + supervisorJob)
    private val ioDispatcher = CoroutineScope(ioDispatcher + supervisorJob)

    fun firebaseLogout(
        callbackLogout: () -> Unit,
        callbackErrorLogout: (error: String) -> Unit
    ) {
        try {
            mainDispatcher.launch {
                ioDispatcher.async {
                    return@async FirebaseAuth.getInstance().signOut()
                }.await()
            }
            callbackLogout.invoke()
        } catch (e: Exception) {
            callbackErrorLogout.invoke(e.toString())
        }
    }

    fun verifySectionFirebase(
        callbackOnSuccessVerifySection: () -> Unit,
        callbackOnErrorVerifySection: () -> Unit
    ) {
        mainDispatcher.launch {
            ioDispatcher.async {
                val userLogged = FirebaseAuth.getInstance().currentUser
                if (userLogged != null){
                    callbackOnErrorVerifySection.invoke()
                } else {
                    callbackOnErrorVerifySection.invoke()
                }
            }.await()
        }
    }

}