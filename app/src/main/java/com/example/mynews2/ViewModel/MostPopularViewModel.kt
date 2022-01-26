package com.example.mynews2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynews2.Model.NewsArticle
import com.example.mynews2.NewsApi.Repository
import com.example.mynews2.NewsApi.RetrofitInstance
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Response

class MostPopularViewModel(private val repository:Repository):ViewModel(){
    val myResponse:MutableLiveData<NewsArticle> = MutableLiveData()

    fun getMostPopularNews(){
        viewModelScope.launch {
            val response:NewsArticle = repository.getMostPopularNews()
            myResponse.value=response
        }
    }




}