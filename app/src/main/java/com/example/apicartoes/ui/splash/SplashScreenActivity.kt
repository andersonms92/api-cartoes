package com.example.apicartoes.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.apicartoes.R
import com.example.apicartoes.ui.login.LoginActivity
import com.example.apicartoes.utils.AuthTags
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashScreenViewModel>()

    val AUTH_TAG = AuthTags.AUTH.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            verify()
        }, 2000)
    }

    private fun verify() {
        viewModel.init()
        viewModel.verifySession.observe(this, {state ->
            when(state){
                is SplashScreenActionView.SessionInitialized -> {
                    startActivity(Intent(this, LoginActivity::class.java)
                        .putExtra(AUTH_TAG, true))
                }

                is SplashScreenActionView.SessionNotInitialized -> {
                    startActivity(Intent(this, LoginActivity::class.java)
                        .putExtra(AUTH_TAG, false))
                }
            }
        })
    }
}