package com.youngsun.imageview_intent_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // FindViewById()를 사용하여 ImageView 객체 생성.
        val img_1 = findViewById<ImageView>(R.id.img_1)
        val img_2 = findViewById<ImageView>(R.id.img_2)
        val img_3 = findViewById<ImageView>(R.id.img_3)
        val img_4 = findViewById<ImageView>(R.id.img_4)
        val img_5 = findViewById<ImageView>(R.id.img_5)
        val img_6 = findViewById<ImageView>(R.id.img_6)
        val img_7 = findViewById<ImageView>(R.id.img_7)




    }



}