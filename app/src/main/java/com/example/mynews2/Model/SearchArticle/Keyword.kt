package com.example.mynews2.Model.SearchArticle

import java.io.Serializable

data class Keyword(
    val major: String,
    val name: String,
    val rank: Int,
    val value: String
):Serializable