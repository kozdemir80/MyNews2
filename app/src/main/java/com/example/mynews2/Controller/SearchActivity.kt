package com.example.mynews2.Controller


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.SearchApi.SearchRespository
import com.example.mynews2.Constants.Constants
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.SearchAdapter
import com.example.mynews2.ViewModel.SearchNewsViewModel
import com.example.mynews2.ViewModel.SearchViewModelFactory
import com.example.mynews2.databinding.SearchItemsBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*
import java.util.Date as Date1


class SearchActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var searchNewsViewModel: SearchNewsViewModel
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: EditText
    private lateinit var binding:SearchItemsBinding

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_items)
        binding = SearchItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchAdapter= SearchAdapter()
        recyclerView=RecyclerView(applicationContext)
        searchView=binding.queryTerm

        var job:Job? = null
        binding.queryTerm.addTextChangedListener {editable->
            job?.cancel()
            job= MainScope().launch {
                delay(Constants.Delay)
                editable?.let {
                    if (editable.toString().isNotEmpty())
                        searchNewsViewModel.getSearchNews()
                }
            }
        }





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
                Log.d("myResponse", response.body()?.response?.docs?.get(0)?.section_name.toString())

                response.body()?.let { searchResponse ->
                    searchAdapter.differ.currentList[0].response.docs[0].abstract

                }
            } else {
                response.errorBody()?.let { Log.d("sResponse", it.string()) }
            }

        }

        val dateRangePicker=MaterialDatePicker.Builder
            .datePicker()
            .setTitleText("Select Date")
            .build()
        val endDateRangePicker=MaterialDatePicker.Builder
            .datePicker()
            .setTitleText("Select Date")
            .build()

            binding.beginDate.setOnClickListener {
                dateRangePicker.show(supportFragmentManager, dateRangePicker.toString())
            }
        dateRangePicker.addOnPositiveButtonClickListener {

            val sdf = SimpleDateFormat("yyyy/MM/dd ", Locale.US)
            val date=sdf.format(it)


            binding.beginDate.setText(date.toString())

        }
        binding.endDate.setOnClickListener {
            endDateRangePicker.show(supportFragmentManager,endDateRangePicker.toString())

        }
        endDateRangePicker.addOnPositiveButtonClickListener() {
            val sdf = SimpleDateFormat("yyyy/MM/dd ", Locale.getDefault())
            val date=sdf.format(it)
            binding.endDate.setText(date.toString())
        }




    }

    override fun onClick(p0: View?) {
        binding.checkboxArts.let {
            it.setOnClickListener(this)
            it.tag = "arts"

        }

        binding.checkboxBusiness.let {
            it.setOnClickListener(this)
            it.tag = "business"
        }
        binding.checkboxEntrepreneurs.let {
            it.setOnClickListener(this)
            it.tag = "entrepreneurs"
        }
        binding.checkboxPolitics.let {
            it.setOnClickListener(this)
            it.tag = "politics"
        }
        binding.checkboxSports.let {
            it.setOnClickListener(this)
            it.tag = "sports"
        }
        binding.checkboxTravel.let {
            it.setOnClickListener(this)
            it.tag = "travel"
        }

        binding.searchQueryButton.setOnClickListener {

        }
    }




}












