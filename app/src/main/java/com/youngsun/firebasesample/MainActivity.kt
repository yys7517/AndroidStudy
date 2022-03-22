package com.youngsun.firebasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.youngsun.firebasesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val btnJoin = binding.btnJoin
        val btnLogin = binding.btnLogin
        val btnLogout = binding.btnLogout

        // 초기에 어플을 실행 시에는 로그인 된 유저 정보가 없으므로 uid 값은 null
        // Toast.makeText(this, "uid = ${auth.currentUser?.uid.toString()}", Toast.LENGTH_SHORT).show()
        if( auth.currentUser?.uid == null ) {
            Toast.makeText(this, "로그인을 하시거나 회원가입을 해주세요.",Toast.LENGTH_SHORT).show()
        }

        // 회원가입 버튼을 클릭했을 때
        btnJoin.setOnClickListener {

            val email = binding.edtEmailArea.text.toString()
            val password = binding.edtPassArea.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                        // 회원가입이 이뤄지고, 자동으로 유저 정보가 저장되면 로그인이 된다.

                        binding.edtEmailArea.text.clear()       // 이메일 입력 창 비우기.
                        binding.edtPassArea.text.clear()        // 비밀번호 입력 창 비우기.

                    } else {
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }


        // 로그인 버튼을 클릭했을 때
        btnLogin.setOnClickListener {

            val email = binding.edtEmailArea.text.toString()
            val password = binding.edtPassArea.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, "uid = ${auth.currentUser?.uid.toString()}", Toast.LENGTH_SHORT).show()

                        binding.edtEmailArea.text.clear()       // 이메일 입력 창 비우기.
                        binding.edtPassArea.text.clear()        // 비밀번호 입력 창 비우기.
                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // 로그아웃 버튼을 클릭했을 때
        btnLogout.setOnClickListener {
            if( auth.currentUser?.uid == null ) {
                Toast.makeText(this, "로그인 정보가 없습니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.signOut()
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                // Toast.makeText(this, "uid = ${auth.currentUser?.uid.toString()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}