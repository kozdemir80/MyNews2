package com.example.mynews2.Model.Notifications

import java.io.Serializable

data class Notifications_Article(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
):Serializable