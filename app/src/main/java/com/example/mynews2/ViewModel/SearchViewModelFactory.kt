package com.example.mynews2.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynews2.Api.Api.SearchApi.SearchRespository

class SearchViewModelFactory(private val respository: SearchRespository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchNewsViewModel(respository) as T
    }
}