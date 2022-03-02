package com.example.mynews2.Model.SearchArticle

import java.io.Serializable

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
):Serializable