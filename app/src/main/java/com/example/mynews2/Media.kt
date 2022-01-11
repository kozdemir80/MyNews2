package com.example.mynews2

data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    val media: List<MediaMetadata>,
    val subtype: String,
    val type: String
)