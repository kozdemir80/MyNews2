package com.example.mynews2.Model.Notifications

import com.google.gson.annotations.SerializedName

data class Byline(

    val organization: Any,
    val original: String,
    val person: List<Person>
)