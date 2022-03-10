package com.youngsun.dice_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.youngsun.dice_app.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    // lateinit var 형식의 binding 변수 선언.
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // binding 변수 초기화.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main )

        binding.diceStartBtn.setOnClickListener{
            // 토스트 메시지 출력.
            Toast.makeText(this, "주사위를 돌립니다.",Toast.LENGTH_LONG).show()

            // 주사위 눈 개수 새로고침.
            // 랜덤 값에 따라 주사위 이미지를 바꾸자.
            val dice1 = Random.nextInt(1,6) // 1 ~ 6 까지 랜덤 수를 가져오겠다.
            val dice2 = Random.nextInt(1,6)

            when(dice1) {
                1 -> binding.dice1.setImageResource(R.drawable.dice_1)
                2 -> binding.dice1.setImageResource(R.drawable.dice_2)
                3 -> binding.dice1.setImageResource(R.drawable.dice_3)
                4 -> binding.dice1.setImageResource(R.drawable.dice_4)
                5 -> binding.dice1.setImageResource(R.drawable.dice_5)
                else -> binding.dice1.setImageResource(R.drawable.dice_6)
            }

            when(dice2) {
                1 -> binding.dice2.setImageResource(R.drawable.dice_1)
                2 -> binding.dice2.setImageResource(R.drawable.dice_2)
                3 -> binding.dice2.setImageResource(R.drawable.dice_3)
                4 -> binding.dice2.setImageResource(R.drawable.dice_4)
                5 -> binding.dice2.setImageResource(R.drawable.dice_5)
                else -> binding.dice2.setImageResource(R.drawable.dice_6)
            }
        }
    }
}