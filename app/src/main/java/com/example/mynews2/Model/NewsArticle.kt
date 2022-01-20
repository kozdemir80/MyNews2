package com.example.mynews2.Model

import com.google.gson.annotations.SerializedName

data class NewsArticle(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val num_results: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("status")
    val status: String
)