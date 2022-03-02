package com.example.mynews2.Model.SearchArticle

import java.io.Serializable

data class Meta(
    val hits: Int,
    val offset: Int,
    val time: Int
):Serializable