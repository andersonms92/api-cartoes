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
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardActivity : Fragment() {

    companion object {
        fun newInstance() = CardActivity()
    }

    private lateinit var rvCards: RecyclerView
    private val viewModel by viewModel<CardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        return inflater.inflate(R.layout.layout_lista_cartoes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as Context

        initViewModel(view, activity)
    }

    private fun initViewModel(view: View, context: Context) {
        viewModel.cardActionView.observe(viewLifecycleOwner, { state ->
            when(state) {
                is CardActionView.SuccessCall -> {
                    if (!state.cardList.isNullOrEmpty()) {
                        setAdapter(view, context, state.cardList)
                    }
                }

                is CardActionView.ErrorCall -> {
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

   private fun setAdapter(view: View, context: Context, list: List<CardModel>) {
       rvCards = view.findViewById(R.id.rv_cartoes_list)
       rvCards.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
       rvCards.adapter = CardAdapter(list)
   }

}