package com.example.mynews2.Model.TopStories

import com.google.gson.annotations.SerializedName

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