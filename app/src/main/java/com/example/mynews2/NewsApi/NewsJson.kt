package com.example.mynews2.NewsApi

data class NewsJson(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)