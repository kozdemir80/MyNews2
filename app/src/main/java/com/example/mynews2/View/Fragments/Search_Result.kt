package com.example.mynews2.View.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.SearchApi.SearchRespository
import com.example.mynews2.Controller.submitList
import com.example.mynews2.Model.SearchArticle.Response
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.SearchAdapter
import com.example.mynews2.ViewModel.SearchNewsViewModel
import com.example.mynews2.ViewModel.SearchViewModelFactory
import com.example.mynews2.databinding.SearchFragmentBinding
import kotlin.math.log

class Search_Fragment:AppCompatActivity(){
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchNewsViewModel:SearchNewsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding:SearchFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_fragment)
        binding = SearchFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
       searchAdapter= SearchAdapter()
        recyclerView=findViewById(R.id.search_recycler_view)
       recyclerView= RecyclerView(applicationContext)


        val getBundle= intent.extras?.getString("search")
        Log.d("mySearch",getBundle.toString())


        recyclerView.adapter=searchAdapter
        recyclerView.layoutManager= LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        searchAdapter.notifyDataSetChanged()






    }
}

