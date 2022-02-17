package com.example.mynews2.View.Fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.navArgs
import com.example.mynews2.Controller.MainActivity
import com.example.mynews2.Model.TopStories.Result
import com.example.mynews2.Model.TopStories.TopStoriesArticle
import com.example.mynews2.R


private lateinit var webView: WebView
class Article_Fragment:Fragment(R.layout.article_fragment){
    val args by navArgs<Article_FragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView=view.findViewById(R.id.webView)
        webView.apply {
            webViewClient= WebViewClient()
            loadUrl(args.mostArticle.toString())
        }




}
}


