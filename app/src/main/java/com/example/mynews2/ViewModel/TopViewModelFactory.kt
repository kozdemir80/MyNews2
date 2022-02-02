package com.example.mynews2.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynews2.TopStoriesApi.TopRepository

class TopViewModelFactory(private val repository: TopRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopStoriesViewModel(repository) as T
    }
}
