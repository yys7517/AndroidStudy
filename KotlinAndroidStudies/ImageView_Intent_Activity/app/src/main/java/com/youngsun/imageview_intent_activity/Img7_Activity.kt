package com.youngsun.imageview_intent_activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Img7_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val image = findViewById<ImageView>(R.id.image)
        image.setImageResource(R.drawable.bts_7)
    }
}