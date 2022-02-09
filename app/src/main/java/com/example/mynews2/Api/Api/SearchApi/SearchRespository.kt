package com.example.mynews2.Api.Api.SearchApi

import com.example.mynews2.Api.Api.BusinessApi.BusinessInstance
import com.example.mynews2.Model.ArticleSearch.SearchTitle
import com.example.mynews2.Model.Business.BusinessArticle
import retrofit2.Response

class SearchRespository{
    suspend fun getSearchArticles(): Response<SearchTitle> {
        return SearchInstance.api.getSearchArticles()
    }
}