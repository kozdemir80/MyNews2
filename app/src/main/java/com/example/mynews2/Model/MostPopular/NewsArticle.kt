package com.example.mynews2.Model.MostPopular

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsArticle(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val num_results: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("status")
    val status: String
): Serializable

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
    var published_date: String,
    @SerializedName("section")
    var section: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("subsection")
    val subsection: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("url")
    val url: String
) : Serializable

data class Media(
    @SerializedName("approved_for_sybdication")
    val approved_for_syndication: Int,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("type")
    val type: String
)

data class MediaMetadata(
    @SerializedName("format")
    val format: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)