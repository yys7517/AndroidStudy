package com.youngsun.mango_contents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter( val List : MutableList<ContentsModel> ) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false )

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems( List[position] )
    }

    override fun getItemCount(): Int {
        return List.size
    }

    inner class ViewHolder( itemView : View ) : RecyclerView.ViewHolder(itemView){
        fun bindItems( item : ContentsModel ) {

        }
    }
}