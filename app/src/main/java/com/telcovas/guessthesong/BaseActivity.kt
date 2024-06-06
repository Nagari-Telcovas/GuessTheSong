package com.telcovas.guessthesong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
abstract class BaseActivity<B : ViewBinding>(val bindingFactory: (LayoutInflater) -> B, title: Int) : AppCompatActivity() {
    private lateinit var binding: B
    var toolbarText: Int = title

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        initialization(binding)
    }

    protected abstract fun initialization(bindingScreen: B)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}