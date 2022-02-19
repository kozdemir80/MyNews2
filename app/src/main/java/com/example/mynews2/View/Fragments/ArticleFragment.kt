package com.example.mynews2.View.Fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.mynews2.Model.TopStories.Result
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.TopStoriesAdapter

class ArticleFragment:Fragment(R.layout.article_fragment){
     private lateinit var webView: WebView
     private lateinit var topStoriesAdapter: TopStoriesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topStoriesAdapter=TopStoriesAdapter()
        webView=view.findViewById(R.id.webView)
        webView=webView
        webView.apply {
            webViewClient= WebViewClient()
            loadUrl(topStoriesAdapter.differ.currentList[0].url)
        }
    }
}