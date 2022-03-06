package com.example.mynews2.Api.Api.SearchApi

import com.example.mynews2.Api.Api.BusinessApi.BusinessInstance


import com.example.mynews2.Model.Business.BusinessArticle
import com.example.mynews2.Model.SearchArticle.SearchTitle
import retrofit2.Response

class SearchRespository {
    suspend fun getSearchArticles(): Response<SearchTitle> {

        return SearchInstance.api.getArticleSearch(
            query = String(),
            beginDate = String(),
            endDate = String(),
            filterQuery = String()
        )
    }
}