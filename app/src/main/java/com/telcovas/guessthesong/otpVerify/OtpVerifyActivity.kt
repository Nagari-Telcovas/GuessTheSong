package com.telcovas.guessthesong.otpVerify


import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.CommonMethods
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.databinding.ActivityOtpVerifyBinding
import com.telcovas.guessthesong.quizMenu.QuizMenuActivity

class OtpVerifyActivity : BaseActivity<ActivityOtpVerifyBinding>(ActivityOtpVerifyBinding::inflate, R.string.verifyOtp) {

    override fun initialization(bindingScreen: ActivityOtpVerifyBinding) {
        bindingScreen.proceedOtp.setOnClickListener {
            CommonMethods.intentCalling(this, QuizMenuActivity())
            finish()
        }
    }
}