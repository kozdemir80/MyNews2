package com.example.mynews2.View.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.TopStoriesApi.TopRepository
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.TopStoriesAdapter
import com.example.mynews2.ViewModel.TopStoriesViewModel
import com.example.mynews2.ViewModel.TopViewModelFactory
import java.lang.NullPointerException

class Top_Stories: Fragment(R.layout.top_stories) {



    private lateinit var topViewModel: TopStoriesViewModel
    private lateinit var topAdapter: TopStoriesAdapter
    private lateinit var recyclerView: RecyclerView









    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=view.findViewById(R.id.top_Stories)
        topAdapter= TopStoriesAdapter()




        topAdapter.setOnItemClickListen {
            val bundle=Bundle().apply {
                putSerializable("topArticle",it)
            }
            findNavController().navigate(
                R.id.action_top_Stories_to_Article_Fragment,bundle
            )
        }







        topAdapter= TopStoriesAdapter()
        recyclerView.adapter=topAdapter
        recyclerView.layoutManager= LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        topAdapter.notifyDataSetChanged()

        val topRepository= TopRepository()
        val TopViewModelFactory= TopViewModelFactory(topRepository)
        topViewModel= ViewModelProvider(this,TopViewModelFactory).get(TopStoriesViewModel::class.java)
        topViewModel.getTopNews()
            topViewModel.mResponse.observe(viewLifecycleOwner, Observer { response->
            if (response.isSuccessful){
                Log.d("mResponse",response.body()?.copyright.toString())
                Log.d("mResponse",response.body()?.status.toString())
                Log.d("mResponse", response.body()?.num_results.toString())
                Log.d("mResponse", response.body()?.results.toString())
                response.body()?.let { newsResponse ->
                    topAdapter.differ.submitList(newsResponse.results)
                }
            }else{
                Log.d("Response", response.errorBody().toString())
            }

        })


    }

}
