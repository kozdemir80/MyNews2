package com.example.mynews2.Controller

import com.example.mynews2.Model.Business.BusinessArticle
import com.example.mynews2.Model.MostPopular.NewsArticle


import com.example.mynews2.Model.TopStories.TopStoriesArticle

import org.junit.Test
import org.junit.runner.RunWith


import org.junit.runners.JUnit4
import org.mockito.Mock



@RunWith(JUnit4::class)
class ResponseUnitTests(){
@Mock
 private lateinit var businessArticle: BusinessArticle
 @Mock
 private lateinit var topStoriesArticle: TopStoriesArticle
 @Mock
 private lateinit var newsArticle: NewsArticle



    @Test
    fun businessResponseTest(){
        businessArticle=BusinessArticle(copyright = String(), last_updated = String(), num_results = 54,
            results = listOf(), section = String(), status = String())
            businessArticle.results.apply {
                businessArticle.section="World"
                businessArticle.results.firstOrNull()?.published_date="2022-04-14:09:30:00:10"
                businessArticle.results.firstOrNull()?.title="X country has declared war"
       }
    }
    @Test
    fun topStoriesResponseTest(){
        topStoriesArticle= TopStoriesArticle(copyright = String(), last_updated = String(),
        num_results = 10, results = listOf(), section = String(), status = String())
        topStoriesArticle.results.apply {
            topStoriesArticle.section="Europe"
            topStoriesArticle.results.firstOrNull()?.published_date="2022-04-10:10:30:00:15"
            topStoriesArticle.results.firstOrNull()?.title="Tension between Europe and Russia"
        }
    }

    @Test
    fun mostPopularResponseTest(){
        newsArticle= NewsArticle(copyright = String(), num_results = 5, results = listOf(), status = String())
        newsArticle.results.apply {
            newsArticle.results.firstOrNull()?.section="world"
            newsArticle.results.firstOrNull()?.published_date="2022-03-29:11:45:00:05"
            newsArticle.results.firstOrNull()?.title="Beautiful spring photos around the world"
        }
    }



}