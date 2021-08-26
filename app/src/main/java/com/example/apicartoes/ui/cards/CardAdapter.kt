package com.example.apicartoes.ui.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.apicartoes.R
import com.example.apicartoes.data.model.CardModel
import com.example.apicartoes.repository.purchases.purchaseFilter
import com.example.apicartoes.utils.ChangeFragment.navigationFragment

class CardAdapter(
    private val contextActivity: FragmentActivity,
    private val list: List<CardModel>
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_list, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class CardViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val cardOwnerName: AppCompatTextView = itemView.findViewById(R.id.actv_card_owner_name)
        private val cardNumber: AppCompatTextView = itemView.findViewById(R.id.actv_card_number)
        private val cardSecurytiCode: AppCompatTextView = itemView.findViewById(R.id.actv_card_security_code)
        private val cardExpirationDate: AppCompatTextView = itemView.findViewById(R.id.actv_card_expiration_date)

        fun bind(cards: CardModel) {

                cardOwnerName.text = cards.name
                cardNumber.text = cards.cardNumber
                cardExpirationDate.text = cards.expirationDate
                cardSecurytiCode.text = cards.code

                itemView.setOnClickListener {
                    purchaseFilter = cards.idCartao
                    onClick(itemView, contextActivity)
                }

        }
    }

    private fun onClick(itemView: View, contextActivity: FragmentActivity) {
        itemView.setOnClickListener {

            navigationFragment(
                contextActivity,
                "PURCHASE_LIST",
                R.id.fl_fragment
            )

        }
    }

    companion object {
        fun newInstance(contextActivity: FragmentActivity) = CardFragment(contextActivity)
    }
}