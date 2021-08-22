package com.example.apicartoes.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apicartoes.R
import com.example.apicartoes.ui.splash.SplashScreenActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_cartoes)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        logout()
    }

    private fun logout() {
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.init()
        viewModel.logoutStatus.observe(this, { state ->
            when(state) {
                is HomeViewAction.LogoutSuccess -> {
                    startActivity(Intent(this, SplashScreenActivity::class.java))
                    finish()
                }

                is HomeViewAction.LogoutError -> {
                    Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}