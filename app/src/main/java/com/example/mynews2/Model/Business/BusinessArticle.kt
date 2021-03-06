package com.example.mynews2.Model.Business

import java.io.Serializable

data class BusinessArticle(
    val copyright: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<Result>,
    var section: String,
    val status: String
): Serializable