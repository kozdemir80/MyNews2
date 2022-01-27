package com.example.mynews2.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Model.NewsArticle
import com.example.mynews2.Model.Result
import com.example.mynews2.NewsApi.Repository
import com.example.mynews2.NewsApi.RetrofitInstance
import com.example.mynews2.R
import com.example.mynews2.R.*
import com.example.mynews2.ViewModel.MostPopularViewModel
import com.example.mynews2.ViewModel.ViewModelFactory
import retrofit2.Response


class Most_Popular:Fragment(layout.most_popular) {


   private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MostPopularViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView= inflater.inflate(layout.most_popular, container, false)
        recyclerView= rootView?.findViewById(R.id.recyclerData)!!
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(activity)
       recyclerView.adapter=NewsAdapter()
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter=NewsAdapter()
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