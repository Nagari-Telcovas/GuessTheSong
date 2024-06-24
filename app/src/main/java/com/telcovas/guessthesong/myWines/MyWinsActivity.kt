package com.telcovas.guessthesong.myWines


import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.CommonMethods
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.apicall.ApiHelperImpl
import com.telcovas.guessthesong.apicall.RetrofitBuilder
import com.telcovas.guessthesong.apicall.UiState
import com.telcovas.guessthesong.apicall.ViewModelFactory
import com.telcovas.guessthesong.dashboard.QuizList
import com.telcovas.guessthesong.dashboard.UserQuizList
import com.telcovas.guessthesong.databinding.ActivityMyWinesBinding

class MyWinsActivity : BaseActivity<ActivityMyWinesBinding>(ActivityMyWinesBinding::inflate, R.string.wins) {

    private val movieList = ArrayList<MyWinsModel>()
    private lateinit var localAdapter: MyWinsAdapter
    private lateinit var selectAmountList: RecyclerView
    private lateinit var winesText: AppCompatTextView
    private lateinit var winViewModel: MyWinsViewModel

    override fun initialization(bindingScreen: ActivityMyWinesBinding) {
        selectAmountList = bindingScreen.selectAmountList
        winesText = bindingScreen.totalWins
        bindingScreen.toolbarLayout.toolbarBack.setOnClickListener {
            finish()
        }
        bindingScreen.toolbarLayout.toolbarmenu.setOnClickListener {
            finish()
        }
       /* localAdapter = MyWinsAdapter(this, movieList)
        val packsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        selectAmountList.layoutManager = packsLayoutManager
        selectAmountList.itemAnimator = DefaultItemAnimator()
        selectAmountList.adapter = localAdapter*/
        setupViewModel()
       // prepareMovieData()
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

    private fun setupViewModel() {
        winViewModel = ViewModelProvider(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService)))[MyWinsViewModel::class.java]

        winViewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    var winsQuizList = it.data.userList as ArrayList<UserQuizList>
                    winesText.text = it.data.maxPoint
                    localAdapter = MyWinsAdapter(this, winsQuizList)
                    selectAmountList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    selectAmountList.itemAnimator = DefaultItemAnimator()
                    selectAmountList.adapter = localAdapter
                    // Log.e("success",":"+it.data)
                    // progressBar.visibility = View.GONE
                    // renderList(it.data)
                }
                is UiState.Loading -> {
                    Log.e("Loading",":1")
                    //   progressBar.visibility = View.VISIBLE

                }
                is UiState.Error -> {
                    CommonMethods.showMessage(this, it.message)
                    //Handle Error
                    // progressBar.visibility = View.GONE
                    Log.e("Error",":roor"+it.message)
                }
            }
        }
    }
}