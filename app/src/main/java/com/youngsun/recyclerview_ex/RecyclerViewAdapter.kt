package com.youngsun.recyclerview_ex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
// ListView와 RecyclerView의 가장 큰 차이점 ?
// ListView의 경우, 매 번 뷰 바인딩을 하여 성능이 저하된다.
// RecyclerView의 경우, ViewHolder 패턴을 강제하여, 뷰 바인딩을 한 번만 해주고,
// 그 후에는 바인딩 된 뷰 객체를 재활용 한다는 장점.

class RecyclerViewAdapter( val items : MutableList<String> ) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)

        val text = view.findViewById<TextView>(R.id.rv_text)
        return ViewHolder(view)
    }

    interface ItemClick {
        fun onClick( view : View, position: Int )
    }
    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        if( itemClick != null ) {
            holder.itemView.setOnClickListener {
                v -> itemClick?.onClick(v, position)
            }
        }
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