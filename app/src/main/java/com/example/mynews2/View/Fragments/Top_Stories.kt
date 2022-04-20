package com.example.mynews2.View.Fragments



import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle


import android.util.Log.*
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Api.Api.TopStoriesApi.TopRepository
import com.example.mynews2.Controller.TopsWebView
import com.example.mynews2.Controller.WebView
import com.example.mynews2.R
import com.example.mynews2.View.Adapters.TopStoriesAdapter
import com.example.mynews2.ViewModel.TopStoriesViewModel
import com.example.mynews2.ViewModel.TopViewModelFactory




import android.content.Intent as Intent1


class Top_Stories: Fragment(R.layout.top_stories) {



    private lateinit var topViewModel: TopStoriesViewModel
    private lateinit var topAdapter: TopStoriesAdapter
    private lateinit var recyclerView: RecyclerView





    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView=view.findViewById(R.id.top_Stories)
        topAdapter= TopStoriesAdapter()

        recyclerView.adapter=topAdapter
        recyclerView.layoutManager= LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        topAdapter.notifyDataSetChanged()





        val topRepository= TopRepository()
        val TopViewModelFactory= TopViewModelFactory(topRepository)
        topViewModel= ViewModelProvider(this,TopViewModelFactory).get(TopStoriesViewModel::class.java)
        topViewModel.getTopNews()
            topViewModel.mResponse.observe(viewLifecycleOwner) { response ->
                if (response.isSuccessful) {
                    d("mResponse", response.body()?.copyright.toString())
                    d("mResponse", response.body()?.status.toString())
                    d("mResponse", response.body()?.num_results.toString())

                    response.body()?.let { newsResponse ->
                        topAdapter.differ.submitList(newsResponse.results)



                        topAdapter.setOnItemClickListener(object :
                            TopStoriesAdapter.onItemClickListener {
                            override fun onItemClick(position: Int) {
                                val preferences = activity?.getSharedPreferences(
                                    "top_stories",
                                    Context.MODE_PRIVATE
                                )
                                val editor = preferences?.edit()
                                editor?.putString("Top_Stories",newsResponse.results[position].url)
                                editor?.apply()


                                val intent =
                                    Intent1(activity,TopsWebView::class.java)
                                startActivity(intent)


                            }


                        })
                    }
                } else {
                    d("Response", response.errorBody().toString())
                }

            }


    }




}











