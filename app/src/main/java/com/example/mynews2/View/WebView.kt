package com.example.mynews2.View

import android.annotation.SuppressLint
import android.content.Context


import android.os.Build


import android.os.Bundle
import android.util.Log


import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


import com.example.mynews2.databinding.ArticleFragmentBinding

private lateinit var binding: ArticleFragmentBinding
private lateinit var webView: WebView
class WebView: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArticleFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = WebView()
        binding.webView.webViewClient = WebViewClient()

        binding.webView.apply {
            val preferences=getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
            val getData:String?=preferences.getString("Top_Stories",null)
            settings.javaScriptEnabled=true
            settings.domStorageEnabled=true
            settings.safeBrowsingEnabled=true
            loadUrl(getData.toString())

        }
       binding.webView.apply {
           val preferences = getSharedPreferences("most_popular", Context.MODE_PRIVATE)
           val mData: String? = preferences.getString("Most_Popular", null)
           settings.javaScriptEnabled = true
           settings.domStorageEnabled = true
           settings.safeBrowsingEnabled = true
           loadUrl(mData.toString())
       }
        binding.webView.apply {
            val preferences=getSharedPreferences("Business_Data", Context.MODE_PRIVATE)
            val bData:String?=preferences.getString("business",null)
            settings.javaScriptEnabled=true
            settings.domStorageEnabled=true
            settings.safeBrowsingEnabled=true
            loadUrl(bData.toString())
        }


    }

}