package com.youngsun.goodwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.youngsun.goodwords.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // DataBinding
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // 전체 명언 보기 버튼 클릭 시 전체 명언 리스트를 보여주는 Activity로 이동.
        binding.showAllSentenceBtn.setOnClickListener{
            val intent = Intent( this, SentenceActivity::class.java )
            startActivity(intent)
        }
    }
}