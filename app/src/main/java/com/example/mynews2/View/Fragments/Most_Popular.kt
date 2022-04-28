package com.example.mynews2.View.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.MostPopularApi.Repository
import com.example.mynews2.Controller.WebView
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.NewsAdapter
import com.example.mynews2.ViewModel.MostPopularViewModel
import com.example.mynews2.ViewModel.ViewModelFactory
// Most popular fragment to display most popular articles
class Most_Popular:Fragment(R.layout.most_popular) {
    private lateinit var viewModel: MostPopularViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter=NewsAdapter()
        recyclerView=view.findViewById(R.id.recyclerData)
        recyclerView.adapter=newsAdapter
        recyclerView.layoutManager=LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        newsAdapter.notifyDataSetChanged()
        val repository= Repository()
        val viewModelFactory= ViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory).get(MostPopularViewModel::class.java)
        viewModel.getMostPopularNews()
        viewModel.myResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                Log.d("Response", response.body()?.copyright.toString())
                Log.d("Response", response.body()?.status.toString())
                Log.d("Response", response.body()?.num_results.toString())

                response.body()?.let { newsResponse ->
                    newsAdapter.differ.submitList(newsResponse.results)
                    newsAdapter.setOnItemClickListener(object : NewsAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val preferences =
                                activity?.getSharedPreferences("most_popular", Context.MODE_PRIVATE)
                            val editor = preferences?.edit()
                                editor?.putString("Most_Popular",newsResponse.results[position].url)
                                editor?.apply()
                            val intent = Intent(activity, WebView::class.java)
                            startActivity(intent)
                        }
                    })
                }
            } else {
                Log.d("Response", response.errorBody().toString())
            }
        }
    }
}