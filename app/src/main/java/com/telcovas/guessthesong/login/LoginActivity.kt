package com.telcovas.guessthesong.login


import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.CommonMethods
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.apicall.ApiHelperImpl
import com.telcovas.guessthesong.apicall.RetrofitBuilder
import com.telcovas.guessthesong.apicall.UiState
import com.telcovas.guessthesong.apicall.ViewModelFactory
import com.telcovas.guessthesong.databinding.ActivityLoginBinding
import com.telcovas.guessthesong.quizMenu.QuizMenuActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate, R.string.login) {
    private lateinit var viewModel: LoginViewModel
    private lateinit var loginProgress: ProgressBar

    override fun initialization(bindingScreen: ActivityLoginBinding) {
        loginProgress = bindingScreen.loginProgress.progressView
        bindingScreen.submitButton.setOnClickListener {
            if (CommonMethods.isEmpty(bindingScreen.phoneNumberOtp)) {
                bindingScreen.phoneNumberOtp.error = getString(R.string.requiredMsisdn)
                bindingScreen.phoneNumberOtp.requestFocus()
            }else if(bindingScreen.phoneNumberOtp.length() < 8) {
                bindingScreen.phoneNumberOtp.error = getString(R.string.numberLength)
                bindingScreen.phoneNumberOtp.requestFocus()
            }
            else {
                CommonMethods.setSharedPreference(this, "countryCode", "+${bindingScreen.countryPicker.selectedCountryCode}")
                CommonMethods.setSharedPreference(this, "msisdn", bindingScreen.phoneNumberOtp.text.toString())
                viewModel.fetchLogin("userLogin", "+${bindingScreen.countryPicker.selectedCountryCode} ${bindingScreen.phoneNumberOtp.text.toString()}", "telcovas")
            }
        }
        setupViewModel()
    }

    private fun setupViewModel() {
        loginProgress.visibility = View.VISIBLE
        viewModel = ViewModelProvider(
            this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService)))[LoginViewModel::class.java]

        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    loginProgress.visibility = View.GONE
                    if(it.data.status == "true") {
                        CommonMethods.intentCalling(this, QuizMenuActivity())
                        finish()
                    }
                    else
                        CommonMethods.showMessage(this,"Please enter valid mobile number")

                }
                is UiState.Loading -> {
                    Log.e("Loading",":1")
                    loginProgress.visibility = View.VISIBLE

                }
                is UiState.Error -> {
                    loginProgress.visibility = View.GONE
                    Log.e("Error",":roor"+it.message)
                    CommonMethods.showMessage(this, it.message)
                }
            }
        }
    }
}