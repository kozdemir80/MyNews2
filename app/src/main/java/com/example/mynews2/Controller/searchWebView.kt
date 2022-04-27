package com.example.mynews2.Controller

import android.content.Context
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.mynews2.databinding.WebviewDisplayBinding
private lateinit var webView: WebView
private lateinit var binding: WebviewDisplayBinding
class searchWebView:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WebviewDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = WebView()
        binding.webView.webViewClient = WebViewClient()
        val mPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)

        binding.webView.apply {
            loadUrl(mPreferences.getString("mSearch", null).toString())

        }

    }
}