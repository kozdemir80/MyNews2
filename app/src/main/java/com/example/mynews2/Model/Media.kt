package com.example.mynews2.Model

import com.google.gson.annotations.SerializedName

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