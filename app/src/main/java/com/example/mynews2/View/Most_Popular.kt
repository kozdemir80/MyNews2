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
import com.example.mynews2.R
import com.example.mynews2.R.*
import com.example.mynews2.ViewModel.MostPopularViewModel
import com.example.mynews2.ViewModel.ViewModelFactory


class Most_Popular:Fragment(layout.most_popular) {


   lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MostPopularViewModel


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
        val repository= Repository()
        val viewModelFactory= ViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory).get(MostPopularViewModel::class.java)
        viewModel.getMostPopularNews()
        viewModel.myResponse.observe(viewLifecycleOwner, { reponse->
            Log.d("Response",reponse.copyright)
            Log.d("Response",reponse.status)
            Log.d("Response", reponse.num_results.toString())
            Log.d("Response", reponse.results.toString())

        })
    }

}