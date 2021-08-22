package com.example.apicartoes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apicartoes.utils.StatusFireBase

sealed class HomeViewAction {
    object LogoutSuccess: HomeViewAction()
    class LogoutError(val error: String): HomeViewAction()
}

class HomeViewModel(
    private val statusFireBase: StatusFireBase
) : ViewModel() {

    private val _logoutStatus by lazy { MutableLiveData<HomeViewAction>() }
    val logoutStatus: LiveData<HomeViewAction>
        get() = _logoutStatus

    fun init() {
        executeLogout()
    }

    private fun executeLogout() {
        statusFireBase.firebaseLogout(::logout, ::logoutError)
    }

    private fun logout() {
        _logoutStatus.postValue(HomeViewAction.LogoutSuccess)
    }

    private fun logoutError(error: String) {
        _logoutStatus.postValue(HomeViewAction.LogoutError(error))
    }
}