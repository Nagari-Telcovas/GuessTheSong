package com.telcovas.guessthesong.myWines


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.R
import java.text.SimpleDateFormat


class MyWinsAdapter(private val context: Context, val productEntityList: List<UserQuizList>)
    : RecyclerView.Adapter<MyWinsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_quiz_name, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bindItems(productEntityList[position], context)
    }

    override fun getItemCount(): Int {
        return productEntityList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(productEntity: UserQuizList, context: Context){
            val countryName = itemView.findViewById<AppCompatTextView>(R.id.matchDate)
            val matchName = itemView.findViewById<AppCompatTextView>(R.id.matchName)
            val scoreNumber = itemView.findViewById<AppCompatTextView>(R.id.scoreNumber)
            val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            // val formatter = SimpleDateFormat("EEEE MMM dd, yyyy HH:mm")
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            val output: String = formatter.format(parser.parse(productEntity.inserted_date))
            countryName.text = output
            matchName.text = "${productEntity.correct_answers}/ ${productEntity.NumberOfquestion}"
            scoreNumber.text = "${productEntity.total_points} pts"
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}