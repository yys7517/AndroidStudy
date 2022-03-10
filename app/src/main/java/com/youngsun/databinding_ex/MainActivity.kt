package com.youngsun.databinding_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.youngsun.databinding_ex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main )

        // 1번 방식 - findViewById
//        val testBtn = findViewById<Button>(R.id.testBtn)
//        testBtn.setOnClickListener{
//            Toast.makeText(this,"버튼 클릭", Toast.LENGTH_SHORT).show()
//        }

        // 2번 방식 - DataBinding
        binding.testBtn.setOnClickListener {
            Toast.makeText(this,"버튼 클릭", Toast.LENGTH_SHORT).show()
        }
    }
}