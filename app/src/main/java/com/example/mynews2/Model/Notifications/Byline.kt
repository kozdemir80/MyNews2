package com.example.mynews2.Model.Notifications

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
)