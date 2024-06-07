package com.telcovas.guessthesong.login


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.telcovas.guessthesong.CommonMethods
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.quizMenu.QuizMenuActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<AppCompatButton>(R.id.submitButton).setOnClickListener {
            CommonMethods.intentCalling(this, QuizMenuActivity())
            finish()
        }
    }
}