package com.example.mynews2.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynews2.Model.NewsArticle
import com.example.mynews2.NewsApi.RetrofitInstance
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MostPopularViewModel:ViewModel(){
      private val retrofitInstance=RetrofitInstance()
      private val disposable=CompositeDisposable()

      val mostPopularNews= MutableLiveData<List<NewsArticle>>()
      val popularError=MutableLiveData<Boolean>()
      val popularLoading=MutableLiveData<Boolean>()


      private fun getPopularData(){
            popularLoading.value=true

            disposable.add(
                  retrofitInstance.getData()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object :DisposableSingleObserver<List<NewsArticle>>(){
                              override fun onSuccess(t: List<NewsArticle>) {
                                    mostPopularNews.value=t
                                    popularError.value=false
                                    popularLoading.value=false
                              }

                              override fun onError(e: Throwable) {
                                    popularLoading.value=false
                                    popularError.value=true
                                    e.printStackTrace()
                              }

                        })
            )
      }

}