package com.example.mynews2.Model.SearchArticle

data class Response(
    val docs: List<Doc>,
    val facets: Facets,
    val meta: Meta
)