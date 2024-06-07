package com.telcovas.guessthesong.myWines


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.R


class MyWinsAdapter(private val context: Context, val productEntityList: List<MyWinsModel>)
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
        fun bindItems(productEntity: MyWinsModel, context: Context){
            val countryName = itemView.findViewById<AppCompatTextView>(R.id.matchDate)
            val matchName = itemView.findViewById<AppCompatTextView>(R.id.matchName)
            val scoreNumber = itemView.findViewById<AppCompatTextView>(R.id.scoreNumber)
            countryName.text = productEntity.getTitle()
            matchName.text = productEntity.getGenre()
            scoreNumber.text = productEntity.getYear()
           /* countryFlag.setImageDrawable(ContextCompat.getDrawable(context, productEntity.getYear()!!))
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