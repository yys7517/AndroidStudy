package com.youngsun.firebasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardWriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        val edtTitle = findViewById<EditText>(R.id.edtTitle)
        val edtContents = findViewById<EditText>(R.id.edtContents)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // 글 작성완료 버튼을 눌렀을 때
        btnSubmit.setOnClickListener {

            val database = Firebase.database
            val myRef = database.getReference("board")

            val title = edtTitle.text.toString()
            val contents = edtContents.text.toString()

            // 값이 계속 수정이 된다.
            // myRef.setValue( BoardModel( title, contents) )

            // 값이 계속 데이터베이스에 삽입이 된다.
            myRef.push().setValue( BoardModel( title, contents) )

            Toast.makeText(this, "게시글 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}