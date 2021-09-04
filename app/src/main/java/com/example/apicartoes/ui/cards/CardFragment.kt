package com.example.apicartoes.ui.cards

import android.content.Context
import android.content.Intent
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
import com.example.apicartoes.data.model.CardModel
import com.example.apicartoes.ui.home.HomeViewAction
import com.example.apicartoes.ui.home.HomeViewModel
import com.example.apicartoes.ui.splash.SplashScreenActivity
import com.example.apicartoes.utils.ChangeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardFragment(private val contextActivity: FragmentActivity) : Fragment() {

    private lateinit var btnBack: AppCompatImageView

    companion object {
        fun newInstance(contextActivity: FragmentActivity) = CardFragment(contextActivity)
    }

    private lateinit var rvCards: RecyclerView
    private val cardViewModel by viewModel<CardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        return inflater.inflate(R.layout.layout_card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as Context

        setView(view)
        initCardViewModel(view, activity)
        setClick()
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

    private fun setClick() {
        btnBack.setOnClickListener {
            activity?.finish()

        }
    }

    private fun setView(view: View) {
        btnBack = view.findViewById(R.id.btn_back_login)
    }

    private fun setCardAdapter(view: View, context: Context, list: List<CardModel>) {
        rvCards = view.findViewById(R.id.rv_cartoes_list)
        rvCards.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCards.adapter = CardAdapter(contextActivity, list)
    }
}