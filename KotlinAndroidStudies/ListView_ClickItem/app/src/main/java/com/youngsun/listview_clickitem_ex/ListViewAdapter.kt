package com.youngsun.listview_clickitem_ex

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter ( val List : MutableList<ListViewModel> ) : BaseAdapter() {
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
       var convertView = convertView
        if( convertView == null ) {
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)
        }

        // ListviewModel( title ,contents )

        // List = [
        // ListviewModel("a","b")
        // ListviewModel("c","d")
        // ListviewModel("e","f")
        // ]

        var title = convertView!!.findViewById<TextView>(R.id.title)
        title.text = List[position].title

        var contents = convertView!!.findViewById<TextView>(R.id.contents)
        contents.text = List[position].contents

        return convertView!!
    }
}