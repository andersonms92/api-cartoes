package com.example.apicartoes.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.apicartoes.ui.cards.CardFragment
import com.example.apicartoes.ui.purchases.PurchasesFragment

object ChangeFragment {

    fun navigationFragment(
        context: FragmentActivity,
        fragment: String?,
        idFragment: Int
    ) {

        lateinit var fragmentNav: Fragment

        when (fragment) {
            FragmentsEnum.PURCHASE_LIST.toString() -> {
                fragmentNav = PurchasesFragment.newInstance(context)
            }
            FragmentsEnum.CARD_LIST.toString() -> {
                fragmentNav = CardFragment.newInstance(context)
            }
        }

        context.supportFragmentManager.beginTransaction().apply {
            replace(idFragment, fragmentNav)
            addToBackStack(null)
            commit()
        }

    }
}