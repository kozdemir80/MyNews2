package com.example.mynews2.Model.TopStories

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TopStoriesArticle(
    val copyright: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<Result>,
    val section: String,
    val status: String,

): Serializable

data class Result(
    val `abstract`: String,
    val byline: String,
    val created_date: String,
    val des_facet: List<String>,
    val geo_facet: List<String>,
    val item_type: String,
    val kicker: String,
    val material_type_facet: String,
    val multimedia: List<Multimedia>,
    val org_facet: List<String>,
    val per_facet: List<String>,
    val published_date: String,
    val section: String,
    val short_url: String,
    val subsection: String,
    val title: String,
    val updated_date: String,
    val uri: String,
    val url: String
) : Serializable

data class Multimedia(
    val caption: String,
    val copyright: String,
    val format: String,
    val height: Int,
    val subtype: String,
    val type: String,
    @SerializedName("url")
    val url_: String,
    val width: Int
)