package com.example.mynews2.View.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.BusinessApi.BusinessRespository
import com.example.mynews2.Api.Api.SearchApi.SearchRespository
import com.example.mynews2.Constants.Constants
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.BusinessAdapter
import com.example.mynews2.View.Adapters.SearchAdapter
import com.example.mynews2.ViewModel.BusinessViewModel
import com.example.mynews2.ViewModel.BusinessViewModelFactory
import com.example.mynews2.ViewModel.SearchNewsViewModel
import com.example.mynews2.ViewModel.SearchViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.search_items) {



    private lateinit var searchNewsViewModel: SearchNewsViewModel
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter=searchAdapter
        editText=view.findViewById(R.id.query_term)

        var job:Job?=null
        editText.addTextChangedListener { editable->
            job?.cancel()
            job= MainScope().launch {
            delay(Constants.Delay)
                editable?.let {
                    if (editable.toString().isNotEmpty()){
                        searchNewsViewModel.searchResponse
                    }
                }
            }
        }



        searchAdapter= SearchAdapter()
        recyclerView.adapter=searchAdapter
        recyclerView.layoutManager= LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        searchAdapter.notifyDataSetChanged()

        val repository= SearchRespository()
        val searViewModelFactory= SearchViewModelFactory(repository)
        searchNewsViewModel=ViewModelProvider(this,searViewModelFactory).get(SearchNewsViewModel::class.java)
        searchNewsViewModel.getSearchNews()
        searchNewsViewModel.searchResponse.observe(viewLifecycleOwner, Observer { response->
            if (response.isSuccessful){
                Log.d("sResponse",response.body()?.copyright.toString())
                Log.d("sResponse",response.body()?.status.toString())
                Log.d("sResponse", response.body()?.response.toString())

                response.body()?.let { newsResponse ->
                    searchAdapter.differ.submitList(newsResponse.status)
                }
            }else{
                Log.d("sResponse", response.errorBody().toString())
            }

        })


    }

}

private fun <T> AsyncListDiffer<T>.submitList(status: String) {

}




