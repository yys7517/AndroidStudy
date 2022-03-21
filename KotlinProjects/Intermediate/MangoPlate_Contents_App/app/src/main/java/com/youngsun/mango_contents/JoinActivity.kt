package com.youngsun.mango_contents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = Firebase.auth

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener {
            val emailArea = findViewById<EditText>(R.id.edtEmail)
            val passArea =  findViewById<EditText>(R.id.edtPass)

            auth.createUserWithEmailAndPassword(emailArea.text.toString(), passArea.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                        val intent = Intent( this, MainActivity::class.java )
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this, "회원가입 실패!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}