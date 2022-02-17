package com.example.mynews2.Model.SearchArticle

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
)