package com.youngsun.recyclerview_ex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter( val items : MutableList<String> ) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)

        val text = view.findViewById<TextView>(R.id.rv_text)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems( items[position] )
    }

    // 리사이클러 뷰의 아이템 개수
    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder( itemView : View ) : RecyclerView.ViewHolder(itemView) {
        fun bindItems( item : String ) {
            val rv_text = itemView.findViewById<TextView>(R.id.rv_text)
            rv_text.text = item
        }
    }
}