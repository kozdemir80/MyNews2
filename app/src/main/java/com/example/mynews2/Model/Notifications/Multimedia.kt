package com.example.mynews2.Model.Notifications

import java.io.Serializable

data class Multimedia(
    val caption: Any,
    val credit: Any,
    val crop_name: String,
    val height: Int,
    val legacy: Legacy,
    val rank: Int,
    val subType: String,
    val subtype: String,
    val type: String,
    val url: String,
    val width: Int
):Serializable