package com.example.mynews2.Model.TopStories

import java.io.Serializable

data class TopStoriesArticle(
    val copyright: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<Result>,
    val section: String,
    val status: String
): Serializable