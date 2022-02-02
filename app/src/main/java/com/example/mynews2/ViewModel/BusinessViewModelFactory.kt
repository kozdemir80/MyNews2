package com.example.mynews2.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynews2.BussinessApi.BusinessRespository
import com.example.mynews2.TopStoriesApi.TopRepository


class BusinessViewModelFactory(private val repository: BusinessRespository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BusinessViewModel(repository) as T
    }
}