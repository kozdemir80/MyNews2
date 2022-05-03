@file:Suppress("NAME_SHADOWING")

package com.example.mynews2.Controller
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.SearchApi.SearchRespository
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.SearchAdapter
import com.example.mynews2.ViewModel.SearchNewsViewModel
import com.example.mynews2.ViewModel.SearchViewModelFactory
import com.example.mynews2.databinding.SearchResultBinding
@Suppress("EqualsBetweenInconvertibleTypes", "UNREACHABLE_CODE")
class Search_Result : AppCompatActivity(){
    private lateinit var searchNewsViewModel: SearchNewsViewModel
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var binding:SearchResultBinding

    @SuppressLint("NewApi", "NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_result)
        binding = SearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchAdapter=SearchAdapter()
        binding.searchRecyclerView.findViewById<RecyclerView>(R.id.search_recycler_view)
        binding.searchRecyclerView.adapter=searchAdapter
        binding.searchRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.searchRecyclerView.setHasFixedSize(true)
       // query, items begin and endDate and categories preferences from search activity
        val preferences=getSharedPreferences("Search Items", MODE_PRIVATE)
        val query = preferences.getString("myQuery",null)
        val beginDate = preferences.getString("beginD",null)
        val endDate =preferences.getString("endD",null)
        val filterQuery = intent.getStringArrayListExtra("myCategories")
        val myCategory= filterQuery.toString().replace("[","").replace("]","")
        val repository= SearchRespository()
        val searViewModelFactory= SearchViewModelFactory(repository)
        searchNewsViewModel= ViewModelProvider(this,searViewModelFactory).get(SearchNewsViewModel::class.java)
               if ( searchNewsViewModel.getSearchNews(
                    query = query!!,
                    beginDate = beginDate!!,
                    endDate =endDate!!,
                    filterQuery = myCategory
                ).isCompleted){
        searchNewsViewModel.searchResponse.observe({ lifecycle }) { response ->
            if (response.isSuccessful) {
                Log.d("sResponse",response.body()?.copyright.toString())
                Log.d("sResponse", response.body()?.status.toString())

                response.body()?.let { searchResponse ->
                    searchAdapter.differ.submitList(searchResponse.response.docs)
                    searchAdapter.setOnItemClickListener(object :
                        SearchAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val preferences =
                                getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
                            val editor = preferences.edit()
                            editor.putString("mSearch",
                                searchResponse.response.docs[position].web_url)
                            editor.apply()

                            val intent =
                                Intent(this@Search_Result, searchWebView::class.java)
                            startActivity(intent)
                        }
                    })
                }
            }else {
                response.errorBody().let {
                    Log.d("eResponse", it.toString())
                }}
            }
        }else{
                   val alertPopUp= AlertDialog.Builder(this)
                       .setTitle("No articles matching your search. Please Try a different keyword")
                       .setPositiveButton("Ok"){_,_->
                       }
                   alertPopUp.create().show()
        }
    }
}