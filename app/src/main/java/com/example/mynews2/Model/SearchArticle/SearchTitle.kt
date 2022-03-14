package com.example.mynews2.Model.SearchArticle

import java.io.Serializable

data class SearchTitle(
    val copyright: String,
    val response: Response,
    val status: String
):Serializable

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
):Serializable

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
    val pub_date: String,
    val section_name: String,
    val snippet: String,
    val source: String,
    val subsection_name: String,
    val type_of_material: String,
    val uri: String,
    val web_url: String,
    val word_count: Int
):Serializable

data class Facets(
    val section_name: SectionName
):Serializable

data class Headline(
    val content_kicker: Any,
    val kicker: Any,
    val main: String,
    val name: Any,
    val print_headline: Any,
    val seo: Any,
    val sub: Any
):Serializable


data class Keyword(
    val major: String,
    val name: String,
    val rank: Int,
    val value: String
):Serializable

data class Legacy(
    val thumbnail: String,
    val thumbnailheight: Int,
    val thumbnailwidth: Int,
    val wide: String,
    val wideheight: Int,
    val widewidth: Int,
    val xlarge: String,
    val xlargeheight: Int,
    val xlargewidth: Int
):Serializable


data class Meta(
    val hits: Int,
    val offset: Int,
    val time: Int
):Serializable

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

data class Person(
    val firstname: String,
    val lastname: String,
    val middlename: Any,
    val organization: String,
    val qualifier: Any,
    val rank: Int,
    val role: String,
    val title: Any
):Serializable

data class Response(
    val docs: List<Docs>,
    val facets: Facets,
    val meta: Meta
) : Serializable

data class SectionName(
    val terms: List<Term>
):Serializable

data class Term(
    val count: Int,
    val term: String
):Serializable