package com.youngsun.mango_contents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val url = intent.getStringExtra("url").toString()

        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl(url)
    }
}