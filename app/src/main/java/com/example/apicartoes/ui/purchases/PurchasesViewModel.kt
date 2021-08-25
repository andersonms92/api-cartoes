package com.example.apicartoes.ui.purchases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apicartoes.data.model.PurchasesModel
import com.example.apicartoes.usecase.purchases.PurchasesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelChildren

sealed class PurchasesActionView {
    class SuccessCall (val purchaseList: List<PurchasesModel>?) : PurchasesActionView()
    class ErrorCall (val error: String) : PurchasesActionView()
}

class PurchasesViewModel (
    val useCase: PurchasesUseCase,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val supervisorJob = SupervisorJob()
    private val mainDispatcher = CoroutineScope(mainDispatcher + supervisorJob)
    private val ioDispatcher = CoroutineScope( ioDispatcher + supervisorJob)

    private val _purchaseActionView by lazy { MutableLiveData<PurchasesActionView>() }
    val purchaseActionView: LiveData<PurchasesActionView>
        get() = _purchaseActionView

    init {
        executeLogin()
    }

    private fun executeLogin() {
        mainDispatcher.launch {
            loginUseCase()
        }
    }

    private suspend fun loginUseCase() {
        ioDispatcher.async {
            return@async useCase.getPurchaseList(::success, ::error)
        }.await()
    }

    private fun success(purchaseList: List<PurchasesModel>?) {
        _purchaseActionView.postValue(PurchasesActionView.SuccessCall(purchaseList))
        GlobalScope.coroutineContext.cancelChildren()
    }

    private fun error(error: String) {
        _purchaseActionView.postValue(PurchasesActionView.ErrorCall(error))
        GlobalScope.coroutineContext.cancelChildren()
    }

}