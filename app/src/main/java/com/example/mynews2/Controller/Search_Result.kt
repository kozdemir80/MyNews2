package com.example.mynews2.Controller


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.SearchApi.SearchRespository
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.SearchAdapter
import com.example.mynews2.ViewModel.SearchNewsViewModel
import com.example.mynews2.ViewModel.SearchViewModelFactory
import com.example.mynews2.databinding.SearchFragmentBinding



class Search_Result : AppCompatActivity(){
    private lateinit var searchNewsViewModel: SearchNewsViewModel
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var binding:SearchFragmentBinding


    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_fragment)
        binding = SearchFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)









        val preferences=getSharedPreferences("myPreferences", MODE_PRIVATE)
        val query = preferences.getString("myQuery",null).toString()
        val beginDate = preferences.getString("beginD",null).toString()
        val endDate =preferences.getString("endD",null).toString()
        val filterQuery = preferences.getBoolean("mArts", true).toString()



        val repository= SearchRespository()
        val searViewModelFactory= SearchViewModelFactory(repository)
        searchNewsViewModel= ViewModelProvider(this,searViewModelFactory).get(SearchNewsViewModel::class.java)
        searchNewsViewModel.getSearchNews(query = query, beginDate = beginDate, endDate =endDate, filterQuery =filterQuery)
        searchNewsViewModel.searchResponse.observe({ lifecycle }) { response ->
            if (response.isSuccessful) {
                Log.d("sResponse",response.body()?.copyright.toString())
                Log.d("sResponse", response.body()?.status.toString())


                response.body()?.let { searchResponse ->
                    recyclerView=findViewById(R.id.search_recycler_view)
                    searchAdapter= SearchAdapter()
                    recyclerView=RecyclerView(baseContext)

                    searchAdapter.differ.submitList(searchResponse.response.toString())
                    recyclerView.adapter=searchAdapter
                    recyclerView.layoutManager= LinearLayoutManager(this)
                    recyclerView.setHasFixedSize(true)
                    searchAdapter.notifyDataSetChanged()
                }
            } else {
                response.errorBody()?.let { Log.d("eResponse", it.string()) }
            }

        }






    }









}

private fun <T> AsyncListDiffer<T>.submitList(status: String) {

}















