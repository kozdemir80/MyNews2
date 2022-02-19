package com.example.mynews2.View.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.BusinessApi.BusinessRespository
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.BusinessAdapter
import com.example.mynews2.ViewModel.BusinessViewModel
import com.example.mynews2.ViewModel.BusinessViewModelFactory


class Business_Fragment : Fragment(R.layout.bussiness) {



    private lateinit var businessViewModel: BusinessViewModel
    private lateinit var businessAdapter: BusinessAdapter
    private lateinit var recyclerView: RecyclerView




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.business)
        businessAdapter=BusinessAdapter()


        businessAdapter= BusinessAdapter()
        recyclerView.adapter=businessAdapter
        recyclerView.layoutManager= LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        businessAdapter.notifyDataSetChanged()

        val repository= BusinessRespository()
        val businessViewModelFactory= BusinessViewModelFactory(repository)
        businessViewModel=ViewModelProvider(this,businessViewModelFactory).get(BusinessViewModel::class.java)
        businessViewModel.getMostPopularNews()
        businessViewModel.myResponse.observe(viewLifecycleOwner, Observer { response->
            if (response.isSuccessful){
                Log.d("myResponse",response.body()?.copyright.toString())
                Log.d("myResponse",response.body()?.status.toString())
                Log.d("myResponse", response.body()?.num_results.toString())
                Log.d("myResponse", response.body()?.results.toString())
                response.body()?.let { newsResponse ->
                    businessAdapter.differ.submitList(newsResponse.results)
                }
            }else{
                Log.d("myResponse", response.errorBody().toString())
            }

        })


    }

}