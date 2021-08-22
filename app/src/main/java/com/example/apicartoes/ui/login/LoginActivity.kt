package com.example.apicartoes.ui.login
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.apicartoes.R
import com.example.apicartoes.ui.home.HomeActivity
import com.example.apicartoes.utils.AuthTags
import com.example.apicartoes.utils.LoginFieldVerification
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.Executor

class LoginActivity : AppCompatActivity(), View.OnClickListener{

    val AUTH_SUCCESS = AuthTags.SUCCESS.toString()
    val AUTH_KEY = AuthTags.AUTH.toString()

    private val viewModel by viewModel<LoginViewModel>()

    lateinit var userName: AppCompatEditText
    lateinit var password: AppCompatEditText
    lateinit var btnLogin: Button
    lateinit var btnBiometricLogin: AppCompatImageView

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var biometricPromptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        verifyAuth()
        initBiometrichAuth()
    }

    private fun initViews() {
        userName = findViewById(R.id.textinputeditext_userName)
        password = findViewById(R.id.textinputeditext_loginPassword)
        btnLogin = findViewById(R.id.buttonLogin)
        btnBiometricLogin = findViewById(R.id.aciv_biometric_login)
    }

    private fun verifyAuth() {
        val extras = intent.extras
        if (extras != null) {
            when(extras.get(AUTH_KEY)) {
                true -> {
                    btnLogin.visibility = View.GONE
                }
                false -> {
                    btnBiometricLogin.visibility = View.GONE
                }
            }
        }
    }

    private fun initBiometrichAuth() {
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(
                errorCode: Int,
                errorString: CharSequence) {

                super.onAuthenticationError(errorCode, errorString)
                btnLogin.visibility = View.VISIBLE
                btnBiometricLogin.visibility = View.GONE
            }

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                callActivity(AUTH_SUCCESS)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(applicationContext, "Falha na autenticação", Toast.LENGTH_LONG).show()
            }
        })

        biometricPromptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Logar com biometria")
            .setNegativeButtonText("Logar com e-mail e senha")
            .build()
    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.buttonLogin ->{
                initViewModel()
            }
            R.id.aciv_biometric_login -> {
                biometricPrompt.authenticate(biometricPromptInfo)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        startActivity(Intent(this,LoggedOutActivity::class.java))
        finish()
    }
    private fun initViewModel() {
        viewModel.init(LoginFieldVerification.verifyField(
            userName.text.toString(),
            password.text.toString(),
            this
        ))

        viewModel.loginActionView.observe(this, { state ->
            when(state) {
                is LoginActionView.LoginSuccess -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                }

                is LoginActionView.LoginError -> {
                    Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun callActivity(activity: String) {
        when (activity) {
            AUTH_SUCCESS -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

//    fun login() {
//
//        if(loginViewModel.isValid(email.text.toString(),senha.text.toString())){
//
//            frame.visibility = View.VISIBLE
//            val loginDados = LoginModelRequest(email.text.toString().toLowerCase(), senha.text.toString())
//            loginViewModel.init(loginDados)
//            loginViewModel.response.observe(this) {
//                if (it.res) {
//                    frame.visibility = View.GONE
//
//                    sessionManagement.initializeSession(it.user.nickname.toString())
//                    startActivity(Intent(this,HomeActivity::class.java))
//
//                } else {
//                    frame.visibility = View.GONE
//                    onAlertDialogLogin(loginViewModel.response.value?.message.toString())
//                }
//            }
//        }else{
//            loginViewModel.messageValidator.observe(this, Observer {
//                    message -> onAlertDialogLogin(getString(message))
//            })
//            loginViewModel.messageValidator.removeObservers((this as AppCompatActivity?)!!)
//        }
//    }
}