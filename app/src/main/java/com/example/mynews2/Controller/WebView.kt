package com.example.mynews2.Controller

import android.annotation.SuppressLint
import android.content.Context


import android.os.Build


import android.os.Bundle

import android.webkit.WebViewClient
import androidx.annotation.RequiresApi


import androidx.appcompat.app.AppCompatActivity
import com.example.mynews2.databinding.WebviewDisplayBinding

private lateinit var binding: WebviewDisplayBinding
private lateinit var webView: WebView
class WebView: AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WebviewDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = WebView()
        binding.webView.webViewClient = WebViewClient()
        popularView()
    }
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun popularView(){
        val preferences = getSharedPreferences("most_popular", Context.MODE_PRIVATE)
        val mData: String? = preferences.getString("Most_Popular", null)
        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.safeBrowsingEnabled = true
            if (mData != null) {
                loadUrl(mData)
            }
        }
    }
}