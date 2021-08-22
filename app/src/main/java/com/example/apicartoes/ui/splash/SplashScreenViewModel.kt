package com.example.apicartoes.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apicartoes.utils.StatusFireBase

sealed class SplashScreenActionView{
    object SessionInitialized : SplashScreenActionView()
    object SessionNotInitialized : SplashScreenActionView()
}

class SplashScreenViewModel(
    private val statusFireBase: StatusFireBase
) : ViewModel() {

    private val _verifySession by lazy { MutableLiveData<SplashScreenActionView>() }
    val verifySession : LiveData<SplashScreenActionView>
        get() = _verifySession

    fun init() {
        executeLogout()
    }

    private fun executeLogout() {
        statusFireBase.verifySectionFirebase(::initialized, :: notInitialized)
    }

    private fun initialized() {
        _verifySession.postValue(SplashScreenActionView.SessionInitialized)
    }

    private fun notInitialized() {
        _verifySession.postValue(SplashScreenActionView.SessionNotInitialized)
    }

}