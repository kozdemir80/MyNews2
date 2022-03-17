package com.example.mynews2.View.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment


import android.view.View




import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider



import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.MostPopularApi.Repository
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.NewsAdapter
import com.example.mynews2.View.Adapters.TopStoriesAdapter
import com.example.mynews2.ViewModel.MostPopularViewModel
import com.example.mynews2.ViewModel.ViewModelFactory
import java.lang.UnsupportedOperationException


class Most_Popular:Fragment(R.layout.most_popular) {



    private lateinit var viewModel: MostPopularViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.recyclerData)

        newsAdapter=NewsAdapter()
        recyclerView= RecyclerView(requireActivity())
      

        newsAdapter= NewsAdapter()
        recyclerView.adapter=newsAdapter
        recyclerView.layoutManager=LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        newsAdapter.notifyDataSetChanged()

        val repository= Repository()
        val viewModelFactory= ViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory).get(MostPopularViewModel::class.java)
        viewModel.getMostPopularNews()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response->
           if (response.isSuccessful){
               Log.d("Response",response.body()?.copyright.toString())
               Log.d("Response",response.body()?.status.toString())
               Log.d("Response", response.body()?.num_results.toString())
               Log.d("Response", response.body()?.results.toString())
               response.body()?.let { newsResponse ->
                   newsAdapter.differ.submitList(newsResponse.results)
               }
           }else{
               Log.d("Response", response.errorBody().toString())
           }

        })


    }

}

