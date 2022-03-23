package com.example.mynews2.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import android.util.Log
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.TypedArrayUtils.getString
import com.example.mynews2.R
import com.example.mynews2.databinding.ArticleFragmentBinding
import com.example.mynews2.databinding.SearchNotificationsBinding

private lateinit var binding: ArticleFragmentBinding
private lateinit var webView: WebView
class WebView: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArticleFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = WebView()
        binding.webView.webViewClient = WebViewClient()

        val preferences= this.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        val getData:String?=preferences.getString("myKey",null)

        binding.webView.apply {
           loadUrl(getData.toString())
            Log.d("myİntent", getData.toString())
        }
    }

}