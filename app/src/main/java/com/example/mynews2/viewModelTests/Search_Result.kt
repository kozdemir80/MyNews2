package com.example.mynews2.viewModelTests


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log


import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*




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
    private lateinit var binding:SearchFragmentBinding


    @SuppressLint("NewApi", "NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_fragment)
        binding = SearchFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchRecyclerView.findViewById<RecyclerView>(R.id.search_recycler_view)
        searchAdapter= SearchAdapter()
        binding.searchRecyclerView.adapter=SearchAdapter()
        binding.searchRecyclerView.layoutManager=LinearLayoutManager(this)









        val preferences=getSharedPreferences("Search Items", MODE_PRIVATE)
        val query = preferences.getString("myQuery",null)
        val beginDate = preferences.getString("beginD",null)
        val endDate =preferences.getString("endD",null)
        val filterQuery = intent.getBooleanExtra("myCategories", true)



        val repository= SearchRespository()
        val searViewModelFactory= SearchViewModelFactory(repository)
        searchNewsViewModel= ViewModelProvider(this,searViewModelFactory).get(SearchNewsViewModel::class.java)
        searchNewsViewModel.getSearchNews(
            query = query!!,
            beginDate = beginDate!!,
            endDate =endDate!!,
            filterQuery = filterQuery.toString()
        )
        searchNewsViewModel.searchResponse.observe({ lifecycle }) { response ->
            if (response.isSuccessful) {
                Log.d("sResponse",response.body()?.copyright.toString())
                Log.d("sResponse", response.body()?.status.toString())
                Log.d("sResponse", response.body()?.response?.docs.toString())

                response.body()?.let { searchResponse ->

                    searchAdapter.differ.submitList(searchResponse.response.docs)





                }
            } else {
                response.errorBody()?.let { Log.d("eResponse", it.string()) }
            }

        }






    }









}

























