package com.youngsun.recyclerview_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
// RecyclerView란?
// 새로운 ViewGroup이며, ListView와 GridView의 기능을 제공.
// ListView의 단점을 해결하기 위해 더 다양한 형태로 커스터마이징 할 수 있도록 제공.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = mutableListOf<String>()

        items.add("a")
        items.add("b")
        items.add("c")

        val rv = findViewById<RecyclerView>(R.id.rv)
        val rvAdapter = RecyclerViewAdapter(items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)

        // 리스트 뷰 처럼 ItemClickListener를 사용해보자.
        // rv.setOnItemClickListener가 없다 !
        rvAdapter.itemClick = object : RecyclerViewAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(baseContext, items[position], Toast.LENGTH_SHORT).show()
                // 이 부분에 RecyclerView 클릭 이벤트를 구현하면 된다.
            }
        }


    }
}