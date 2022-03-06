package com.example.mynews2.Controller

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.contains
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.*
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.SearchApi.SearchRespository
import com.example.mynews2.Constants.Constants
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.SearchAdapter


import com.example.mynews2.ViewModel.SearchNewsViewModel
import com.example.mynews2.ViewModel.SearchViewModelFactory
import com.example.mynews2.databinding.SearchItemsBinding


import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class SearchActivity : AppCompatActivity() {
    private lateinit var searchNewsViewModel: SearchNewsViewModel
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: EditText
    private lateinit var binding:SearchItemsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_items)
        binding = SearchItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchAdapter= SearchAdapter()
        recyclerView=RecyclerView(applicationContext)
        searchView=binding.queryTerm







        searchAdapter= SearchAdapter()
        recyclerView.adapter=searchAdapter
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        searchAdapter.notifyDataSetChanged()

        val repository= SearchRespository()
        val searViewModelFactory= SearchViewModelFactory(repository)
        searchNewsViewModel= ViewModelProvider(this,searViewModelFactory).get(SearchNewsViewModel::class.java)
        searchNewsViewModel.getSearchNews()
        searchNewsViewModel.searchResponse.observe({ lifecycle }) { response ->
            if (response.isSuccessful) {
                Log.d("myResponse", response.body()?.copyright.toString())
                Log.d("myResponse", response.body()?.status.toString())
                Log.d("myResponse", response.body()?.response.toString())

                response.body()?.let { searchResponse ->
                    searchAdapter.differ.currentList[0].response.docs[0].abstract

                }
            } else {
                response.errorBody()?.let { Log.d("sResponse", it.string()) }
            }

        }


        val c =Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val dayOfMonth=c.get(Calendar.DAY_OF_MONTH)
        val myFormat="dd-MM-yyy"
        val sdf=SimpleDateFormat(myFormat,Locale.FRANCE)
        binding.beginDate.setOnClickListener() {
            val dpd = DatePickerDialog(
                this,
                { view, mYear, mMonth, mDayofMonth ->

                },year, month, dayOfMonth)
            dpd.show()
            binding.beginDate.setText(sdf.format(c.time))
        }
        binding.endDate.setOnClickListener(){
            val dpd=DatePickerDialog(this, { view, mYear, mMonth, mDayofMonth ->

            },year, month, dayOfMonth)
            dpd.show()
            binding.endDate.setText(sdf.format(c.time))
        }


    }

}












