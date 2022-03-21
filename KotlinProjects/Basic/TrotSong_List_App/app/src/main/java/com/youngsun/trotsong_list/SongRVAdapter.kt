package com.youngsun.trotsong_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class SongRVAdapter( val items : MutableList< String > ) : RecyclerView.Adapter<SongRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from( parent.context ).inflate(R.layout.rv_item, parent, false )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems( items[position] )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder( itemView: View ) : RecyclerView.ViewHolder(itemView) {
        fun bindItems( item : String ) {
            val rvTitle = itemView.findViewById<TextView>(R.id.rvTitle)

            rvTitle.text = item
        }
    }
}