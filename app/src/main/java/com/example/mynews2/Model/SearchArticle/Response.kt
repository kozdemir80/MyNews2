package com.example.mynews2.Model.SearchArticle

import java.io.Serializable

data class Response(
    val docs: List<Docs>,
    val facets: Facets,
    val meta: Meta
) : Serializable