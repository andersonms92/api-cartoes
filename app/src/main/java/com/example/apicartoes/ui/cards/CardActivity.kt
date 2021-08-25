package com.example.apicartoes.ui.cards

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicartoes.R
import com.example.apicartoes.data.model.CardModel
import com.example.apicartoes.data.model.PurchasesModel
import com.example.apicartoes.ui.purchases.PurchasesActionView
import com.example.apicartoes.ui.purchases.PurchasesAdapter
import com.example.apicartoes.ui.purchases.PurchasesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardActivity : Fragment() {

    companion object {
        fun newInstance() = CardActivity()
    }

    private lateinit var rvCards: RecyclerView
    private lateinit var rvPurchases: RecyclerView
    private val cardViewModel by viewModel<CardViewModel>()
    private val purchaseViewModel by viewModel<PurchasesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        return inflater.inflate(R.layout.layout_card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as Context

        initCardViewModel(view, activity)
        initPurchaseViewModel(view, activity)
    }

    private fun initCardViewModel(view: View, context: Context) {
        cardViewModel.cardActionView.observe(viewLifecycleOwner, { state ->
            when(state) {
                is CardActionView.SuccessCall -> {
                    if (!state.cardList.isNullOrEmpty()) {
                        setCardAdapter(view, context, state.cardList)
                    }
                }

                is CardActionView.ErrorCall -> {
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initPurchaseViewModel(view: View, context: Context) {
        purchaseViewModel.purchaseActionView.observe(viewLifecycleOwner, { state ->
            when(state) {
                is PurchasesActionView.SuccessCall -> {
                    if (!state.purchaseList.isNullOrEmpty()) {
                        setPurchaseAdapter(view, context, state.purchaseList)
                    }
                }

                is PurchasesActionView.ErrorCall -> {
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setCardAdapter(view: View, context: Context, list: List<CardModel>) {
        rvCards = view.findViewById(R.id.rv_cartoes_list)
        rvCards.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCards.adapter = CardAdapter(list)

    }

    private fun setPurchaseAdapter(view: View, context: Context, list: List<PurchasesModel>) {
        rvPurchases = view.findViewById(R.id.rv_purchases_list)
        rvPurchases.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvPurchases.adapter = PurchasesAdapter(list)
    }
}