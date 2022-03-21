package com.youngsun.databinding_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.youngsun.databinding_ex.databinding.ActivityMainBinding

//  DataBinding
/*
    findViewById()의 경우, 런타임 시 뷰의 계층을 타고 들어가는 방식이라
    앱이 커질 경우 비효율적인 함수이다.
    DataBinding은 각 레이아웃마다 binding class를 만들게 되고, binding 클래스를 통해 쉽게 뷰에 접근할 수 있다.
    MVVM 아키텍쳐에서 주로 사용하는 방식.
 */
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