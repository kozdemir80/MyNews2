package com.example.mynews2.Model.Notifications

import java.io.Serializable

data class Multimedia(
    val caption: String,
    val copyright: String,
    val format: String,
    val height: Int,
    val subtype: String,
    val type: String,
    val url: String,
    val width: Int
):Serializable