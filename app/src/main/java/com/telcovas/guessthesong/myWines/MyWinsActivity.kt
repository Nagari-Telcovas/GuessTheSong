package com.telcovas.guessthesong.myWines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.databinding.ActivityMyWinesBinding

class MyWinsActivity : BaseActivity<ActivityMyWinesBinding>(ActivityMyWinesBinding::inflate, R.string.wins) {

    private val movieList = ArrayList<MyWinsModel>()
    private lateinit var localAdapter: MyWinsAdapter
    private lateinit var selectAmountList: RecyclerView

    override fun initialization(bindingScreen: ActivityMyWinesBinding) {
        selectAmountList = bindingScreen.selectAmountList
        bindingScreen.toolbarLayout.toolbarBack.setOnClickListener {
            finish()
        }
        localAdapter = MyWinsAdapter(this, movieList)
        val packsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        selectAmountList.layoutManager = packsLayoutManager
        selectAmountList.itemAnimator = DefaultItemAnimator()
        selectAmountList.adapter = localAdapter
        prepareMovieData()
    }

    private fun prepareMovieData() {
        var movie = MyWinsModel("10/5/2024", "Quiz Name 01", "20/20")
        movieList.add(movie)
        movie = MyWinsModel("11/5/2024", "Quiz Name 02", "19/20")
        movieList.add(movie)
        movie = MyWinsModel("12/5/2024", "Quiz Name 03", "18/20")
        movieList.add(movie)
        movie = MyWinsModel("13/5/2024", "Quiz Name 04", "17/20")
        movieList.add(movie)
        movie = MyWinsModel("14/5/2024", "Quiz Name 05", "16/20")
        movieList.add(movie)
        movie = MyWinsModel("15/5/2024", "Quiz Name 06", "20/20")
        movieList.add(movie)
        movie = MyWinsModel("16/5/2024", "Quiz Name 07", "15/20")
        movieList.add(movie)
        movie = MyWinsModel("17/5/2024", "Quiz Name 08", "18/20")
        movieList.add(movie)
        localAdapter.notifyDataSetChanged()
    }
}