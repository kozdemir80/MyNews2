package com.example.mynews2.Model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("abstract")
    val `abstract`: String,
    @SerializedName("adx_keywords")
    val adx_keywords: String,
    @SerializedName("asset_id")
    val asset_id: Long,
    @SerializedName("byline")
    val byline: String,
    @SerializedName("column")
    val column: Any,
    @SerializedName("des_facet")
    val des_facet: List<String>,
    @SerializedName("eta_id")
    val eta_id: Int,
    @SerializedName("geo_facet")
    val geo_facet: List<String>,
    @SerializedName("id")
    val id: Long,
    @SerializedName("media")
    val media: List<Media>,
    @SerializedName("nytdsection")
    val nytdsection: String,
    @SerializedName("org_facet")
    val org_facet: List<String>,
    @SerializedName("per_facet")
    val per_facet: List<String>,
    @SerializedName("published_date")
    val published_date: String,
    @SerializedName("section")
    val section: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("subsection")
    val subsection: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("url")
    val url: String
)