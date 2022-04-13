package com.example.mynews2.Controller

import com.example.mynews2.Model.Business.BusinessArticle

import org.junit.Test
import org.junit.runner.RunWith


import org.junit.runners.JUnit4
import org.mockito.Mock



@RunWith(JUnit4::class)
class ResponseUnitTests(){
@Mock
 private lateinit var businessArticle: BusinessArticle


    @Test
    fun topStoriesResponseTest(){
        businessArticle=BusinessArticle(copyright = String(), last_updated = String(), num_results = 54,
            results = listOf(), section = String(), status = String())
            businessArticle.results.apply {
                businessArticle.section="World"
                businessArticle.results.firstOrNull()?.published_date="2022-04-14:09:30:00:10"
                businessArticle.results.firstOrNull()?.title="X country has declared war"
       }
    }



}