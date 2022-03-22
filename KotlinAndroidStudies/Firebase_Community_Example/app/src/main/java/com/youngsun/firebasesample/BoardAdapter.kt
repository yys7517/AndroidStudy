package com.youngsun.firebasesample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BoardAdapter ( val List : MutableList<BoardModel> ) : BaseAdapter() {
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(position: Int): Any {
        return List[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if( view == null ) {
            view = LayoutInflater.from(parent?.context).inflate( R.layout.board_item, parent, false)
        }

        val titleArea = view!!.findViewById<TextView>(R.id.LV_Title)
        val contentsArea = view!!.findViewById<TextView>(R.id.LV_Contents)

        titleArea.text = List[position].title
        contentsArea.text = List[position].contents

        return view!!
    }
}