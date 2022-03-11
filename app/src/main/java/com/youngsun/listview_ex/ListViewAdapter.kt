package com.youngsun.listview_ex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.text.FieldPosition

// Adapter - ListView의 각 item의 값을 할당해준다.

class ListViewAdapter( val list : MutableList<ListViewModel> ) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position : Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
        // Adapter와 item 레이아웃 연결

        var converView = converView
        if( converView == null ) {
            converView = LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)
        }

        val title = converView!!.findViewById<TextView>(R.id.listviewItem)
        val content = converView!!.findViewById<TextView>(R.id.listviewItem2)
        title.text = list[position].title
        content.text = list[position].content
        // list[0] -> ListViewModel("a","b")
        // list[1] -> ListViewModel("c","d")
        // list[2] -> ListViewModel("e","f")

        return converView!!
    }
}