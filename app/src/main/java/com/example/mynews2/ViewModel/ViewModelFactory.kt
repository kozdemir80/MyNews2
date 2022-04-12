package com.example.mynews2.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynews2.Api.Api.MostPopularApi.Repository

class ViewModelFactory(private val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MostPopularViewModel(repository) as T
    }
}