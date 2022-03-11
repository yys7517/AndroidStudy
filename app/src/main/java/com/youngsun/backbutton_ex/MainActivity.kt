package com.youngsun.backbutton_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var isPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    
    override fun onBackPressed() {
        Log.d("MainActivity","BackButtonPressed")

        // 이미 뒤로가기 버튼이 눌렸었다면, 한 번 더 눌렸을 때, App을 종료한다.
        if( isPressed == true ) {
            finish()
        }

        // 뒤로가기 버튼이 눌렸으면, isPressed 를 true로 초기화한다.
        isPressed = true
        Toast.makeText(this,"종료하시려면 더블클릭",Toast.LENGTH_SHORT).show()

        // 뒤로가기 버튼이 눌린 후, 2000ms(2s)동안 누르지 않는다면, 다시 isPressed값을 false로 초기화한다.
        Handler().postDelayed( Runnable {
            isPressed = false
        },2000)

    }

}