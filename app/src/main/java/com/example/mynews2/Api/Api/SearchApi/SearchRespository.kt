package com.example.mynews2.Api.Api.SearchApi


import com.example.mynews2.Model.SearchArticle.SearchTitle
import retrofit2.Response
import java.util.*

class SearchRespository {
    suspend fun getSearchArticles(
        query: String,
        beginDate:String,
        endDate: String,
        filterQuery: String
    ): Response<SearchTitle> {

        return SearchInstance.api.getArticleSearch(query,beginDate,endDate, filterQuery)
    }
}