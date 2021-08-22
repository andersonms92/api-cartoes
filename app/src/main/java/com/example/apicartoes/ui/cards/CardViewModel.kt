package com.example.apicartoes.ui.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apicartoes.data.model.CardModel
import com.example.apicartoes.usecase.cards.CardUseCase
import kotlinx.coroutines.*

sealed class CardActionView {
    class SuccessCall (val cardList: List<CardModel>?) : CardActionView()
    class ErrorCall (val error: String) : CardActionView()
}

class CardViewModel (
    val useCase: CardUseCase,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val supervisorJob = SupervisorJob()
    private val mainDispatcher = CoroutineScope(mainDispatcher + supervisorJob)
    private val ioDispatcher = CoroutineScope( ioDispatcher + supervisorJob)

    private val _cardActionView by lazy { MutableLiveData<CardActionView>() }
    val cardActionView: LiveData<CardActionView>
        get() = _cardActionView

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
            return@async useCase.getListCards(::success, ::error)
        }.await()
    }

    private fun success(cardList: List<CardModel>?) {
        _cardActionView.postValue(CardActionView.SuccessCall(cardList))
        GlobalScope.coroutineContext.cancelChildren()
    }

    private fun error(error: String) {
        _cardActionView.postValue(CardActionView.ErrorCall(error))
        GlobalScope.coroutineContext.cancelChildren()
    }

}