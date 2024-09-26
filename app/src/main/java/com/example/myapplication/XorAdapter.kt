package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class XorAdapter(
    val xorList: List<Xor>
) :RecyclerView.Adapter<XorAdapter.XorViewHolder>() {
    inner class XorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_xor, parent, false) //attachToRoot must always be false for a recycle view
        return XorViewHolder(view)
    }

    override fun onBindViewHolder(holder: XorViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvX1_value).text = String.format("%.3f", xorList[position].x1)
            findViewById<TextView>(R.id.tvX2_value).text = String.format("%.3f", xorList[position].x2)
            findViewById<TextView>(R.id.tvXOR_value).text = xorList[position].xor
        }
    }

    override fun getItemCount(): Int {
        return xorList.size
    }
}