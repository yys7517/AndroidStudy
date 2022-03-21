package com.youngsun.firebase_login_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        // 익명 로그인 버튼
        val AnonymousLoginBtn = findViewById<Button>(R.id.anonymousLoginBtn)

        AnonymousLoginBtn.setOnClickListener {
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Log.d("MainActivity", user!!.uid)   // kjyPvkwtNZVkifWYserKi2Jeytj1

                    } else {
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                    }
                }
        }

        // 이메일, 패스워드 입력 창
        val emailArea = findViewById<EditText>(R.id.emailArea)
        val passArea = findViewById<EditText>(R.id.passArea)

        // 회원가입 버튼
        val joinBtn = findViewById<Button>(R.id.joinBtn)

        joinBtn.setOnClickListener {
            Log.d("MAIN", "입력한 Email : " + emailArea.text.toString())
            Log.d("MAIN", "입력한 Password : " + passArea.text.toString())

            auth.createUserWithEmailAndPassword(emailArea.text.toString(), passArea.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // 이메일 패스워드 로그인 버튼
        val emailPassLoginBtn = findViewById<Button>(R.id.emailPassLoginBtn)

        emailPassLoginBtn.setOnClickListener {
            Log.d("MAIN", "입력한 Email : " + emailArea.text.toString())
            Log.d("MAIN", "입력한 Password : " + passArea.text.toString())

            auth.signInWithEmailAndPassword(emailArea.text.toString(), passArea.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // 로그아웃 버튼
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)

        logoutBtn.setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(this, "로그아웃 완료!",Toast.LENGTH_SHORT).show()
        }
    }



}