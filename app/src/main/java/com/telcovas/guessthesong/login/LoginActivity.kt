package com.telcovas.guessthesong.login


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.telcovas.guessthesong.CommonMethods
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.apicall.ApiHelperImpl
import com.telcovas.guessthesong.apicall.RetrofitBuilder
import com.telcovas.guessthesong.apicall.UiState
import com.telcovas.guessthesong.apicall.ViewModelFactory
import com.telcovas.guessthesong.dashboard.MainViewModel
import com.telcovas.guessthesong.quizMenu.QuizMenuActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    lateinit var phoneNumber: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        phoneNumber = findViewById(R.id.phoneNumberOtp)

        findViewById<AppCompatButton>(R.id.submitButton).setOnClickListener {
           // setupViewModel()
            if (CommonMethods.isEmpty(phoneNumber)) {
                phoneNumber.error = getString(R.string.requiredMsisdn)
                phoneNumber.requestFocus()
            }else if(phoneNumber.length() < 8) {
                phoneNumber.error = getString(R.string.numberLength)
                phoneNumber.requestFocus()
            }
            else {
                viewModel.fetchLogin("userLogin", phoneNumber.text.toString(), "telcovas")
            }
        }
        setupViewModel()
    }
    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)

            )
        )[LoginViewModel::class.java]

        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    Log.e("success",":"+it.data.status)
                    // progressBar.visibility = View.GONE
                    // renderList(it.data)
                    if(it.data.status=="true") {
                        CommonMethods.intentCalling(this, QuizMenuActivity())
                        finish()
                    }
                    else
                        Toast.makeText(this,"Please enter valid mobile number",Toast.LENGTH_SHORT).show()

                }
                is UiState.Loading -> {
                    Log.e("Loading",":1")
                    //   progressBar.visibility = View.VISIBLE

                }
                is UiState.Error -> {
                    //Handle Error
                    // progressBar.visibility = View.GONE
                    Log.e("Error",":roor"+it.message)
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}