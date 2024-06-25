package com.telcovas.guessthesong.leaderBoard


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.R


class LeaderBoardAdapter(private val context: Context, val productEntityList: List<LeaderBoardItem>)
    : RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_leader_board, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bindItems(productEntityList[position], context, position+1)
        //holder.bind(productEntityList[position], clickListener, clickListenerImage, context)
    }

    override fun getItemCount(): Int {
        return productEntityList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView/*binding.root*/) {
        fun bindItems(productEntity: LeaderBoardItem, context: Context, currentPos: Int){
            val countryName = itemView.findViewById<AppCompatTextView>(R.id.positionNumber)
            val matchName = itemView.findViewById<AppCompatTextView>(R.id.matchName)
            val scoreText = itemView.findViewById<AppCompatTextView>(R.id.scoreText)
          //  val positionNumberText = itemView.findViewById<AppCompatTextView>(R.id.positionNumberText)
            countryName.text = currentPos.toString()
            scoreText.text = "${productEntity.total_points} pts"
            matchName.text = productEntity.userId
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}