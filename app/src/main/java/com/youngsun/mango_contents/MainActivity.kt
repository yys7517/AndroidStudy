package com.youngsun.mango_contents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val items = mutableListOf<ContentsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/ByuIW33rXc",
                "https://mp-seoul-image-production-s3.mangoplate.com/219116/488522_1639565714575_24422?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "알라프리마"
            )
        )

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/ByuIW33rXc",
                "https://mp-seoul-image-production-s3.mangoplate.com/219116/488522_1639565714575_24422?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "알라프리마"
            )
        )

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/ByuIW33rXc",
                "https://mp-seoul-image-production-s3.mangoplate.com/219116/488522_1639565714575_24420?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "알라프리마"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val rvAdapter = RVAdapter(baseContext, items)
        recyclerView.adapter = rvAdapter
        // recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}