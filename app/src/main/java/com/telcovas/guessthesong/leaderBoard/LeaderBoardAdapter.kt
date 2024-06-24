package com.telcovas.guessthesong.leaderBoard


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.R


class LeaderBoardAdapter(private val context: Context, val productEntityList: List<LeaderBoardOutput>)
    : RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_leader_board, viewGroup, false)
        return ViewHolder(v)
        /*val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding : ItemUserFeedBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_user_feed, viewGroup, false)

        return ViewHolder(binding)*/
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bindItems(productEntityList[position], context, position+1)
        //holder.bind(productEntityList[position], clickListener, clickListenerImage, context)
    }

    override fun getItemCount(): Int {
        return productEntityList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView/*binding.root*/) {
        fun bindItems(productEntity: LeaderBoardOutput, context: Context, currentPos: Int){
            val countryName = itemView.findViewById<AppCompatTextView>(R.id.positionNumber)
            val matchName = itemView.findViewById<AppCompatTextView>(R.id.matchName)
            val scoreText = itemView.findViewById<AppCompatTextView>(R.id.scoreText)
          //  val positionNumberText = itemView.findViewById<AppCompatTextView>(R.id.positionNumberText)
            countryName.text = currentPos.toString()
            //positionNumberText.text = productEntity.total_points.toString()
          //  matchName.text = productEntity.userId
         //   scoreText.text = "${productEntity.total_points} pts"
         /*   countryFlag.setImageDrawable(ContextCompat.getDrawable(context, productEntity.getYear()!!))
            itemView.setOnClickListener {
                val iValidate = Intent(context, CountryWisePackActivity::class.java)
                iValidate.putExtra("CountryName", productEntity.getTitle())
                context.startActivity(iValidate)
            }*/
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}