package com.telcovas.guessthesong.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.model.SongClickListener

class QuizListAdapter(private val context: Context, val priceList: List<String>, private var clickItemListener: SongClickListener) :
    RecyclerView.Adapter<QuizListAdapter.ViewHolder>() {
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
        holder?.currentText?.text = (position+1).toString()
        holder?.contentQuestion?.text = priceList.get(position)
        if(position == selectedItemPos)
            holder?.selQuestion?.visibility= View.VISIBLE
        else
            holder?.selQuestion?.visibility= View.GONE

        holder?.itemView?.setOnClickListener {
            selectedItemPos = holder.adapterPosition
            if(lastItemSelectedPos == -1)
                lastItemSelectedPos = selectedItemPos
            else {
                notifyItemChanged(lastItemSelectedPos)
                lastItemSelectedPos = selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
            clickItemListener.onSelectClicked(priceList.get(position), priceList.get(position))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val currentText = view.findViewById(R.id.currentText) as AppCompatTextView
        val contentQuestion = view.findViewById(R.id.contentQuestion) as AppCompatTextView
        val selQuestion = view.findViewById(R.id.selectedQuestion) as AppCompatImageView
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}