package com.example.mynews2.Model.SearchArticle

import java.io.Serializable

data class SearchTitle(
    val copyright: String,
    val response: Response,
    val status: String
):Serializable