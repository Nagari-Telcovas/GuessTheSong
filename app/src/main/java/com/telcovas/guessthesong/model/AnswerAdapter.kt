package com.telcovas.guessthesong.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.R


class AnswerAdapter(private val context: Context, val priceList: List<Detail>) :
    RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {
    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    override fun getItemCount(): Int {
        return priceList.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_answer, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.currentText?.text =priceList.get(0).option1
        holder?.itemView?.setOnClickListener {
        //    selectedItemPos = holder.absoluteAdapterPosition
            lastItemSelectedPos = if(lastItemSelectedPos == -1)
                selectedItemPos
            else {
                notifyItemChanged(lastItemSelectedPos)
                selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val currentText = view.findViewById(R.id.currentText) as AppCompatTextView
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}