package com.example.apicartoes.ui.purchases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apicartoes.R
import com.example.apicartoes.data.model.CardModel
import com.example.apicartoes.data.model.PurchasesModel

class PurchasesAdapter(
    private val list: List<PurchasesModel>
) : RecyclerView.Adapter<PurchasesAdapter.PurchasesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasesAdapter.PurchasesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_purchase_list, parent, false)
        return PurchasesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchasesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class PurchasesViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val purchaseValue: AppCompatTextView = itemView.findViewById(R.id.actv_purchase_value)

        fun bind(purchases: PurchasesModel) {

                    purchaseValue.text = purchases.valor
        }
        }
    }