package com.youngsun.listview_clickitem_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list_item = mutableListOf<ListViewModel>()
        list_item.add(ListViewModel("a","b"))
        list_item.add(ListViewModel("c","d"))
        list_item.add(ListViewModel("e","f"))

        val listView = findViewById<ListView>(R.id.listview)
        listView.adapter = ListViewAdapter(list_item)

        // 리스트 뷰 Item 클릭 리스너
        listView.setOnItemClickListener { parent, view, position, id ->
            // 이 곳에 리스트 뷰의 Item이 선택되었을 때 실행할 코드를 작성하면 됨.
        }
    }
}