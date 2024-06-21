package com.telcovas.guessthesong.login


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<AppCompatButton>(R.id.submitButton).setOnClickListener {
            setupViewModel()
          //  viewModel.fetchLogin("userLogin","9032364590","telcovas")
            CommonMethods.intentCalling(this, QuizMenuActivity())
            finish()
        }
      //  setupViewModel()
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
                    Log.e("success",":"+it.data)
                    // progressBar.visibility = View.GONE
                    // renderList(it.data)
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