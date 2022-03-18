package com.youngsun.mango_contents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ViewActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        auth = Firebase.auth

        val url = intent.getStringExtra("url").toString()
        val title = intent.getStringExtra("title").toString()
        val imageUrl = intent.getStringExtra("imageUrl").toString()

        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl(url)

        val database = Firebase.database
        val myBookmarkRef = database.getReference("bookmark_ref")   // path

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.visibility = View.VISIBLE

        // "북마크 저장" 버튼이 눌렸을 때
        saveBtn.setOnClickListener {
            Log.d("URL", url)
            myBookmarkRef
                .child( auth.currentUser!!.uid )    // "DB / bookmark_ref / uid / 북마크 사이트" 형식으로 북마크를 저장.
                .push()
                .setValue(ContentsModel( url, imageUrl, title))

            Toast.makeText(baseContext, "북마크 담기 성공!", Toast.LENGTH_SHORT).show()
        }


    }
}