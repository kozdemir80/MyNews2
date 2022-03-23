package com.example.mynews2.View.Fragments



import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.TopStoriesApi.TopRepository
import com.example.mynews2.Model.TopStories.Result


import com.example.mynews2.R
import com.example.mynews2.View.Adapters.TopStoriesAdapter
import com.example.mynews2.ViewModel.TopStoriesViewModel
import com.example.mynews2.ViewModel.TopViewModelFactory

import java.lang.UnsupportedOperationException


import android.content.Intent as Intent1


class Top_Stories: Fragment(R.layout.top_stories) {



    private lateinit var topViewModel: TopStoriesViewModel
    private lateinit var topAdapter: TopStoriesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var webView: WebView




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView=view.findViewById(R.id.top_Stories)
        topAdapter= TopStoriesAdapter()




        recyclerView.adapter=topAdapter
        recyclerView.layoutManager= LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        topAdapter.notifyDataSetChanged()

        val topRepository= TopRepository()
        val TopViewModelFactory= TopViewModelFactory(topRepository)
        topViewModel= ViewModelProvider(this,TopViewModelFactory).get(TopStoriesViewModel::class.java)
        topViewModel.getTopNews()
            topViewModel.mResponse.observe(viewLifecycleOwner, Observer { response->
            if (response.isSuccessful){
                d("mResponse",response.body()?.copyright.toString())
                d("mResponse",response.body()?.status.toString())
                d("mResponse", response.body()?.num_results.toString())
                d("mResponse", response.body()?.results.toString())
                response.body()?.let { newsResponse ->
                    topAdapter.differ.submitList(newsResponse.results)
                    val preferences= this.getActivity()?.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
                    val editor=preferences?.edit()

                    editor?.apply {
                        putString("myKey", topAdapter.differ.currentList[1].url)
                    }?.apply()

                    topAdapter.setOnItemClickListener(object :TopStoriesAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {


                            val intent=Intent1(activity,com.example.mynews2.View.WebView::class.java)
                            startActivity(intent)
                            Log.d("myArticle",preferences.toString())


                        }


                    })
                }
            }else{
                d("Response", response.errorBody().toString())
            }

        })


    }




}
