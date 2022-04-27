package com.example.mynews2.View.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View


import androidx.lifecycle.ViewModelProvider


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.BusinessApi.BusinessRepository


import com.example.mynews2.Controller.WebViewBusiness
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.BusinessAdapter




import com.example.mynews2.ViewModel.BusinessViewModel
import com.example.mynews2.ViewModel.BusinessViewModelFactory


class Business_Fragment : Fragment(R.layout.bussiness) {
    private lateinit var businessViewModel: BusinessViewModel
    private lateinit var businessAdapter: BusinessAdapter
    private lateinit var recyclerView: RecyclerView
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.business)
        businessAdapter= BusinessAdapter()
        recyclerView.adapter=businessAdapter
        recyclerView.layoutManager= LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        businessAdapter.notifyDataSetChanged()
        val repository= BusinessRepository()
        val businessViewModelFactory= BusinessViewModelFactory(repository)
        businessViewModel=ViewModelProvider(this,businessViewModelFactory).get(BusinessViewModel::class.java)
        businessViewModel.getBusinessNews()
        businessViewModel.myResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                Log.d("myResponse", response.body()?.copyright.toString())
                Log.d("myResponse", response.body()?.status.toString())
                Log.d("myResponse", response.body()?.num_results.toString())
                response.body()?.let { newsResponse ->
                    businessAdapter.differ.submitList(newsResponse.results)
                    businessAdapter.setOnItemClickListener(object :
                        BusinessAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val preferences = activity?.getSharedPreferences(
                                "Business_Data",
                                Context.MODE_PRIVATE
                            )
                            val editor = preferences?.edit()
                            editor?.putString("business",newsResponse.results[position].url)
                            editor?.apply()
                            val intent = Intent(activity, WebViewBusiness::class.java)
                            startActivity(intent)
                        }
                    })
                }
            } else {
                Log.d("myResponse", response.errorBody().toString())
            }
        }
    }

}