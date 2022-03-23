package com.example.mynews2.Model.Notifications

import java.io.Serializable

data class Result(
    val `abstract`: String,
    val byline: String,
    val created_date: String,
    val des_facet: Any,
    val first_published_date: String,
    val geo_facet: Any,
    val item_type: String,
    val kicker: String,
    val material_type_facet: String,
    val multimedia: List<Multimedia>,
    val org_facet: Any,
    val per_facet: Any,
    val published_date: String,
    val related_urls: Any,
    val section: String,
    val slug_name: String,
    val source: String,
    val subheadline: String,
    val subsection: String,
    val thumbnail_standard: String,
    val title: String,
    val updated_date: String,
    val uri: String,
    val url: String
):Serializable