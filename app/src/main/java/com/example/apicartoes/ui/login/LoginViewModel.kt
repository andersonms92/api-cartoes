package com.example.apicartoes.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apicartoes.data.model.login.FirebaseModel
import com.example.apicartoes.usecase.login.LoginUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren

sealed class LoginActionView{
    object LoginSuccess: LoginActionView()
    class LoginError(val error: String): LoginActionView()
}

class LoginViewModel(
    val useCase: LoginUseCase,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val supervisorJob = SupervisorJob()
    private val mainDispatcher = CoroutineScope(mainDispatcher + supervisorJob)
    private val ioDispatcher = CoroutineScope(ioDispatcher + supervisorJob)

    private val _loginActionView by lazy { MutableLiveData<LoginActionView>() }
    val loginActionView: LiveData<LoginActionView>
        get() = _loginActionView

    fun init(login: FirebaseModel) {
        mainDispatcher.launch {
            loginUseCase(login)
        }
    }

    private suspend fun loginUseCase(login: FirebaseModel) {
        ioDispatcher.async {
            return@async useCase.executeLoginFireBase(login, ::success, ::error)
        }.await()
    }

    private fun success() {
        _loginActionView.postValue(LoginActionView.LoginSuccess)
        coroutineContext.cancelChildren()
    }

    private fun error(error: String) {
        _loginActionView.postValue(LoginActionView.LoginError(error))
        coroutineContext.cancelChildren()
    }

}