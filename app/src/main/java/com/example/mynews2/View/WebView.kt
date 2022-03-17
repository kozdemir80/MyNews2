package com.example.mynews2.View

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
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

        val bundle=Bundle()
        binding.webView.apply {
            loadUrl(bundle.getString("TopArticle").toString())
            Log.d("myİntent", bundle.toString())
        }
    }

}