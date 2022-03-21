package com.youngsun.cardview_intent_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val image = findViewById<ImageView>(R.id.image)

        val intent = getIntent()
        val data = intent.getIntExtra("data",0)

        when(data) {
            1 -> image.setImageResource(R.drawable.member_1)
            2 -> image.setImageResource(R.drawable.member_2)
            3 -> image.setImageResource(R.drawable.member_3)
            4 -> image.setImageResource(R.drawable.member_4)
            5 -> image.setImageResource(R.drawable.member_5)
            6 -> image.setImageResource(R.drawable.member_6)
            7 -> image.setImageResource(R.drawable.member_7)
            8 -> image.setImageResource(R.drawable.member_8)
            9 -> image.setImageResource(R.drawable.member_9)
            else -> Toast.makeText(this,"오류",Toast.LENGTH_SHORT).show()
        }
    }
}