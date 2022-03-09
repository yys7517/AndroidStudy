package com.youngsun.cardview_intent_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 이미지를 클릭했을 때, 이미지마다 각각 Activity를 할당시켜주지 않고,
        // 하나의 Activity인 ImageActivity로 클릭된 Image의 데이터를 전달(Intent)하여 이미지를 크게 보여준다.

        val member1 = findViewById<ImageView>(R.id.member1)
        member1.setOnClickListener {
            val intent = Intent( this, ImageActivity::class.java)
            intent.putExtra("data",1)
            startActivity(intent)

        }

        val member2 = findViewById<ImageView>(R.id.member2)
        member2.setOnClickListener {
            val intent = Intent( this, ImageActivity::class.java)
            intent.putExtra("data",2)
            startActivity(intent)

        }

        val member3 = findViewById<ImageView>(R.id.member3)
        member3.setOnClickListener {
            val intent = Intent( this, ImageActivity::class.java)
            intent.putExtra("data",3)
            startActivity(intent)

        }

        val member4 = findViewById<ImageView>(R.id.member4)
        member4.setOnClickListener {
            val intent = Intent( this, ImageActivity::class.java)
            intent.putExtra("data",4)
            startActivity(intent)

        }

        val member5 = findViewById<ImageView>(R.id.member5)
        member5.setOnClickListener {
            val intent = Intent( this, ImageActivity::class.java)
            intent.putExtra("data",5)
            startActivity(intent)

        }

        val member6 = findViewById<ImageView>(R.id.member6)
        member6.setOnClickListener {
            val intent = Intent( this, ImageActivity::class.java)
            intent.putExtra("data",6)
            startActivity(intent)

        }

        val member7 = findViewById<ImageView>(R.id.member7)
        member7.setOnClickListener {
            val intent = Intent( this, ImageActivity::class.java)
            intent.putExtra("data",7)
            startActivity(intent)

        }

        val member8 = findViewById<ImageView>(R.id.member8)
        member8.setOnClickListener {
            val intent = Intent( this, ImageActivity::class.java)
            intent.putExtra("data",8)
            startActivity(intent)

        }

        val member9 = findViewById<ImageView>(R.id.member9)
        member9.setOnClickListener {
            val intent = Intent( this, ImageActivity::class.java)
            intent.putExtra("data",9)
            startActivity(intent)

        }
    }
}