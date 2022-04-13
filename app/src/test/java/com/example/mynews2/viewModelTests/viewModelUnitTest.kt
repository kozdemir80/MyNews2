package com.example.mynews2.viewModelTests

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.CheckBox
import android.widget.EditText
import com.example.mynews2.Model.Business.BusinessArticle
import com.example.mynews2.Model.TopStories.Result
import com.example.mynews2.Model.TopStories.TopStoriesArticle
import com.example.mynews2.View.Fragments.Search_Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import java.util.Collections.list

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