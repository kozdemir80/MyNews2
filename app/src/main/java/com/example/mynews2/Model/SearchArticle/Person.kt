package com.example.mynews2.Model.SearchArticle

import java.io.Serializable

data class Person(
    val firstname: String,
    val lastname: String,
    val middlename: Any,
    val organization: String,
    val qualifier: Any,
    val rank: Int,
    val role: String,
    val title: Any
):Serializable