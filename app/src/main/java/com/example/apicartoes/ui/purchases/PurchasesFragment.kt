package com.example.apicartoes.ui.purchases

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicartoes.R
import com.example.apicartoes.data.model.PurchasesModel
import com.example.apicartoes.utils.ChangeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PurchasesFragment(private val contextActivity: FragmentActivity) : Fragment() {

    private lateinit var btnBack: AppCompatImageView

    companion object {
        fun newInstance(contextActivity: FragmentActivity) = PurchasesFragment(contextActivity)
    }

    private lateinit var rvPurchases: RecyclerView
    private val purchaseViewModel by viewModel<PurchasesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        return inflater.inflate(R.layout.layout_purchase_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as Context

        setView(view)
        initPurchaseViewModel(view, activity)
        setClick()
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

    private fun setClick() {
        btnBack.setOnClickListener {
            ChangeFragment.navigationFragment(
                contextActivity,
                "CARD_LIST",
                R.id.fl_fragment
            )
        }
    }

    private fun setView(view: View) {
        btnBack = view.findViewById(R.id.btn_back_card_list)
    }

    private fun setPurchaseAdapter(view: View, context: Context, list: List<PurchasesModel>) {
        rvPurchases = view.findViewById(R.id.rv_purchases_list)
        rvPurchases.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvPurchases.adapter = PurchasesAdapter(contextActivity, list)
    }
}