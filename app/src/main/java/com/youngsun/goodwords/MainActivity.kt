package com.youngsun.goodwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.youngsun.goodwords.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // DataBinding
    private lateinit var binding : ActivityMainBinding
    private var isPressed : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val senteceList = mutableListOf<String>()
        senteceList.add("검정화면에 대충 흰 글씨 쓰면 명언같다.")
        senteceList.add("사람에게 하나의 입과 두 개의 귀가 있는 것은 마하기보다 듣기를 두 배로 하라는 뜻이다.")
        senteceList.add("결점이 없는 친구를 사귀려고 한다면 평생 친구를 가질 수 없을 것이다.")
        senteceList.add("자기 아이에게 육체적 노동을 가르치지 않는 것은 약탈과 강도를 가르치는 것과 마찬가지다.")
        senteceList.add("승자는 눈을 밟아 길을 만들지만 패자는 눈이 녹기를 기다린다.")
        senteceList.add("두 개의 화살을 갖지 마라. 두 번째 화살이 있기 때문에 첫 번째 화살에 집중하지 않게 된다.")
        senteceList.add("그 사람 입장에 서기 전까지 절대 그 사람을 욕하거나 책망하지 마라.")
        senteceList.add("뛰어난 말에게도 채찍이 필요하다.")

        // MainActivity의 명언 초기 값을 설정.
        // sentenceList에서 랜덤한 값을 꺼내오자 -> senteceList.random()
        binding.showSentenceText.setText( senteceList.random() )

        // 전체 명언 보기 버튼 클릭 시 전체 명언 리스트를 보여주는 Activity로 이동.
        binding.showAllSentenceBtn.setOnClickListener{
            val intent = Intent( this, SentenceActivity::class.java )
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {

        if( isPressed ) {
            finish()
        }

        isPressed = true
        Toast.makeText(this,"종료하려면 한 번 더 뒤로가기를 눌러주세요.",Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            isPressed = false
        },2000)
    }
}