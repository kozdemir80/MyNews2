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
import kotlin.math.log

private lateinit var binding: WebviewDisplayBinding
private lateinit var webView: WebView
@SuppressLint("SetJavaScriptEnabled")
@RequiresApi(Build.VERSION_CODES.O)
class WebViewBusiness:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WebviewDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = WebView()
        binding.webView.webViewClient = WebViewClient()

        val bPreferences=getSharedPreferences("Business_Data", Context.MODE_PRIVATE)

        binding.webView.apply {
            loadUrl(bPreferences.getString("business",null).toString())
            Log.d("myYY",bPreferences.getString("business",null).toString())
        }
    }





}