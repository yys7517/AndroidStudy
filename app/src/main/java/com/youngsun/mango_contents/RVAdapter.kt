package com.youngsun.mango_contents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVAdapter( val context : Context, val List : MutableList<ContentsModel> ) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

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
            val rv_Image = itemView.findViewById<ImageView>(R.id.rvImage)
            val rv_Text = itemView.findViewById<TextView>(R.id.rvTitle)

            // 이미지는 어떻게 설정할까 ? 이미지 주소를 사용 Glide
            Glide.with( context )
                .load( item.titleImageUrl )
                .into(rv_Image)

            rv_Text.text = item.titleText
        }
    }
}