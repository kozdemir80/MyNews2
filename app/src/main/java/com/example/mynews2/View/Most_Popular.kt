package com.example.mynews2.View

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Model.NewsArticle
import com.example.mynews2.Model.Result
import com.example.mynews2.NewsApi.Repository
import com.example.mynews2.R
import com.example.mynews2.R.*
import com.example.mynews2.ViewModel.MostPopularViewModel
import com.example.mynews2.ViewModel.ViewModelFactory
import okhttp3.internal.notify


class Most_Popular:Fragment(R.layout.most_popular) {


    val mrecyclerView = view?.findViewById<RecyclerView>(R.id.recyclerData)
    private lateinit var viewModel: MostPopularViewModel
    lateinit var newsAdapter: NewsAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerview()
        val repository= Repository()
        val viewModelFactory= ViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory).get(MostPopularViewModel::class.java)
        viewModel.getMostPopularNews()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { reponse->
            Log.d("Response",reponse.copyright)
            Log.d("Response",reponse.status)
            Log.d("Response", reponse.num_results.toString())
            Log.d("Response", reponse.results.toString())

        })
    }

    private fun setUpRecyclerview(){
        newsAdapter=NewsAdapter()
        mrecyclerView?.apply {
            adapter=newsAdapter
            layoutManager=LinearLayoutManager(activity)
        }
    }
}