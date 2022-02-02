package com.example.mynews2.TopStoriesApi

import com.example.mynews2.Model.TopStories.TopStoriesArticle
import retrofit2.Response

class TopRepository {

    suspend fun getTopStories(): Response<TopStoriesArticle> {
        return TopInstance.topApi.getTopNews()
    }
}