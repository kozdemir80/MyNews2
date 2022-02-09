package com.example.mynews2.Model.ArticleSearch

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
)