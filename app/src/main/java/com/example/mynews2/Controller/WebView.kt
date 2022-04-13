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
class WebView: AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WebviewDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = WebView()
        binding.webView.webViewClient = WebViewClient()
        topView()
        bView()
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
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun bView(){
        val bPreferences=getSharedPreferences("Business_Data", Context.MODE_PRIVATE)
        val bData:String?=bPreferences.getString("business",null)
        binding.webView.apply {


            settings.javaScriptEnabled=true
            settings.domStorageEnabled=true
            settings.safeBrowsingEnabled=true
            if (bData != null) {
                loadUrl(bData)
            }
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun topView(){
        val mPreferences = getSharedPreferences("top_stories", Context.MODE_PRIVATE)
        val getData: String? = mPreferences.getString("Top_Stories", null)
        Log.d("myTop", getData.toString())
        binding.webView.apply {

            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.safeBrowsingEnabled = true
            if (getData != null) {
                loadUrl(getData)
            }
        }
    }
}