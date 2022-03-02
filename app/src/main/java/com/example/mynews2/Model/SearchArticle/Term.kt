package com.example.mynews2.Model.SearchArticle

import java.io.Serializable

data class Term(
    val count: Int,
    val term: String
):Serializable