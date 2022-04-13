package com.example.mynews2.viewModelTests

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

        binding.webView.apply {
            val preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
            val getData: String? = preferences.getString("Top_Stories", null)
            Log.d("myTop", getData.toString())
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.safeBrowsingEnabled = true
            if (getData != null) {
                loadUrl(getData)
            }
        }
       binding.webView.apply {
           val preferences = getSharedPreferences("most_popular", Context.MODE_PRIVATE)
           val mData: String? = preferences.getString("Most_Popular", null)
           Log.d("mPopular",mData.toString())
           settings.javaScriptEnabled = true
           settings.domStorageEnabled = true
           settings.safeBrowsingEnabled = true
           if (mData != null) {
               loadUrl(mData)
           }
       }
        binding.webView.apply {
            val preferences=getSharedPreferences("Business_Data", Context.MODE_PRIVATE)
            val bData:String?=preferences.getString("business",null)
            Log.d("myBusiness",bData.toString())
            settings.javaScriptEnabled=true
            settings.domStorageEnabled=true
            settings.safeBrowsingEnabled=true
            if (bData != null) {
                loadUrl(bData)
            }
        }


    }

}