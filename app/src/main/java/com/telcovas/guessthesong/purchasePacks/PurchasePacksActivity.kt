package com.telcovas.guessthesong.purchasePacks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.model.AnswerAdapter
import com.telcovas.guessthesong.model.Detail
import com.telcovas.guessthesong.model.PackClickListener
import com.telcovas.guessthesong.model.PackageList
import com.telcovas.guessthesong.model.Packinfo
import com.telcovas.guessthesong.model.Quizinfo
import com.telcovas.guessthesong.model.SongsList

class PurchasePacksActivity : AppCompatActivity() , PackClickListener {

    lateinit var listPackage: PackageList
    private lateinit var packageList: RecyclerView
    lateinit var packAdapter: PackageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_packs)

        packageList = findViewById(R.id.packageList)
        packageList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val jsonString = readPackages()
        listPackage = Gson().fromJson(jsonString, PackageList::class.java)
        setUpAdapterData(listPackage.response)
    }

    private fun readPackages(): String {
        val file_name = "quitlist.json"
        val bufferReader = application.assets.open(file_name).bufferedReader()
        val data = bufferReader.use {
            it.readText()
        }
        // Log.d("readFromAsset", data)

        return data;
    }

    private fun setUpAdapterData(listData: List<Packinfo>){


        //var quizListData22 =quizListData.get(0).option1 as ArrayList<Detail>
        Log.d("setUpAdapterData", listData.toString())
         packAdapter = PackageAdapter(this, listData, this)
        packageList.adapter = packAdapter

    }

    override fun onSelectClicked(optionName: String?, type: String?) {
        TODO("Not yet implemented")
    }
}