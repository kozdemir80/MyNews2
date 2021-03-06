package com.example.mynews2.Model.Notifications

data class Docs(
    val _id: String,
    val `abstract`: String,
    val byline: Byline,
    val document_type: String,
    val headline: Headline,
    val keywords: List<Keyword>,
    val lead_paragraph: String,
    val multimedia: List<Multimedia>,
    val news_desk: String,
    var pub_date: String,
    var section_name: String,
    val snippet: String,
    val source: String,
    val subsection_name: String,
    val type_of_material: String,
    val uri: String,
    val web_url: String,
    val word_count: Int
)