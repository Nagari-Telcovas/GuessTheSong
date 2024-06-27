package com.telcovas.guessthesong.selectPrizes

import android.content.Intent
import androidx.core.content.ContextCompat
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.CommonMethods
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.dashboard.MainActivity
import com.telcovas.guessthesong.databinding.ActivitySelectPrizesBinding

class SelectPrizesActivity : BaseActivity<ActivitySelectPrizesBinding>(ActivitySelectPrizesBinding::inflate, R.string.app_name) {

    var selectedOption = ""

    override fun initialization(bindingScreen: ActivitySelectPrizesBinding) {
        bindingScreen.forDataText.setOnClickListener {
            selectedOption = bindingScreen.forDataText.text.toString()
            bindingScreen.forDataText.background = ContextCompat.getDrawable(this, R.drawable.shape_select_edit)
            bindingScreen.forMobileText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
            bindingScreen.forHeadsetText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
        }
        bindingScreen.forMobileText.setOnClickListener {
            selectedOption = bindingScreen.forMobileText.text.toString()
            bindingScreen.forMobileText.background = ContextCompat.getDrawable(this, R.drawable.shape_select_edit)
            bindingScreen.forDataText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
            bindingScreen.forHeadsetText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
        }
        bindingScreen.forHeadsetText.setOnClickListener {
            selectedOption = bindingScreen.forHeadsetText.text.toString()
            bindingScreen.forHeadsetText.background = ContextCompat.getDrawable(this, R.drawable.shape_select_edit)
            bindingScreen.forMobileText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
            bindingScreen.forDataText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
        }
        bindingScreen.submitButton.setOnClickListener {
            CommonMethods.showMessage(this, selectedOption)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}