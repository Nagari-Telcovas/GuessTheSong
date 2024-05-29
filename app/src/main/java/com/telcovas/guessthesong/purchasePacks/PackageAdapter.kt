package com.telcovas.guessthesong.purchasePacks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.model.PackClickListener
import com.telcovas.guessthesong.model.Packinfo

class PackageAdapter(private val context: Context, val priceList: List<Packinfo>, private var clickItemListener: PackClickListener) :
    RecyclerView.Adapter<PackageAdapter.ViewHolder>() {
    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    override fun getItemCount(): Int {
        return priceList.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_purchase_packs, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.songTitle?.text = priceList.get(position).plan
        holder?.totalCount?.text = priceList.get(position).songs
        holder?.totalAmount?.text = priceList.get(position).price

      /*  if (position == selectedItemPos)
            holder?.totalAmount?.visibility = View.VISIBLE
        else
            holder?.totalAmount?.visibility = View.GONE
*/
        holder?.itemView?.setOnClickListener {
            selectedItemPos = holder.adapterPosition
            if (lastItemSelectedPos == -1)
                lastItemSelectedPos = selectedItemPos
            else {
                notifyItemChanged(lastItemSelectedPos)
                lastItemSelectedPos = selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
            clickItemListener.onSelectClicked(
                priceList.get(position).songs,
                priceList.get(position).plan
            )
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val songTitle = view.findViewById(R.id.songTitle) as AppCompatTextView
        val totalCount = view.findViewById(R.id.totalCount) as AppCompatTextView
        val totalAmount = view.findViewById(R.id.totalAmount) as AppCompatTextView
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}