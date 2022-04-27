package com.example.mynews2.Controller

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.mynews2.databinding.WebviewDisplayBinding
private lateinit var binding: WebviewDisplayBinding
private lateinit var webView: WebView
class TopsWebView:AppCompatActivity(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WebviewDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = WebView()
        binding.webView.webViewClient = WebViewClient()
        val mPreferences = getSharedPreferences("top_stories", Context.MODE_PRIVATE)
        binding.webView.apply {
            loadUrl(mPreferences.getString("Top_Stories", null).toString())
            }
    }

}