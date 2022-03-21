package com.youngsun.imageview_intent_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

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

        // ImageView 클릭 시 이벤트 발생 리스너 생성.

        // 1번 이미지
        img_1.setOnClickListener {
            // Image 클릭 시, Toast 메시지 생성.
            Toast.makeText(this, "1번 클릭 완료",Toast.LENGTH_SHORT).show()

            // Image 클릭 시, Activity 전환 후, 사진 크게 보여주기.
            var intent = Intent( this, Img1_Activity::class.java )
            startActivity(intent)
        }

        // 2번 이미지
        img_2.setOnClickListener {
            // Image 클릭 시, Toast 메시지 생성.
            Toast.makeText(this, "2번 클릭 완료",Toast.LENGTH_SHORT).show()

            // Image 클릭 시, Activity 전환 후, 사진 크게 보여주기.
            var intent = Intent( this, Img2_Activity::class.java )
            startActivity(intent)
        }

        // 3번 이미지
        img_3.setOnClickListener {
            // Image 클릭 시, Toast 메시지 생성.
            Toast.makeText(this, "3번 클릭 완료",Toast.LENGTH_SHORT).show()

            // Image 클릭 시, Activity 전환 후, 사진 크게 보여주기.
            var intent = Intent( this, Img3_Activity::class.java )
            startActivity(intent)
        }

        // 4번 이미지
        img_4.setOnClickListener {
            // Image 클릭 시, Toast 메시지 생성.
            Toast.makeText(this, "4번 클릭 완료",Toast.LENGTH_SHORT).show()

            // Image 클릭 시, Activity 전환 후, 사진 크게 보여주기.
            var intent = Intent( this, Img4_Activity::class.java )
            startActivity(intent)
        }

        // 5번 이미지
        img_5.setOnClickListener {
            // Image 클릭 시, Toast 메시지 생성.
            Toast.makeText(this, "5번 클릭 완료",Toast.LENGTH_SHORT).show()

            // Image 클릭 시, Activity 전환 후, 사진 크게 보여주기.
            var intent = Intent( this, Img5_Activity::class.java )
            startActivity(intent)
        }

        // 6번 이미지
        img_6.setOnClickListener {
            // Image 클릭 시, Toast 메시지 생성.
            Toast.makeText(this, "6번 클릭 완료",Toast.LENGTH_SHORT).show()

            // Image 클릭 시, Activity 전환 후, 사진 크게 보여주기.
            var intent = Intent( this, Img6_Activity::class.java )
            startActivity(intent)
        }
        // 7번 이미지
        img_7.setOnClickListener {
            // Image 클릭 시, Toast 메시지 생성.
            Toast.makeText(this, "7번 클릭 완료",Toast.LENGTH_SHORT).show()

            // Image 클릭 시, Activity 전환 후, 사진 크게 보여주기.
            var intent = Intent( this, Img7_Activity::class.java )
            startActivity(intent)
        }



    }
}