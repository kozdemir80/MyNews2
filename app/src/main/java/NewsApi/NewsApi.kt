package NewsApi

import androidx.contentpager.content.Query
import com.example.mynews2.Constants.Companion.APİ_KEY
import com.example.mynews2.NewsResponse
import retrofit2.Response
import retrofit2.http.GET


interface NewsApi {
    @GET("mostpopular/v2/emailed/{period}.json")


    suspend fun getMostPopularNews(
        @retrofit2.http.Query("country")
        countryCode: String = "us",
        @retrofit2.http.Query("page")
        pageNumber: Int = 1,
        @retrofit2.http.Query("apiKey")
        apiKey:String=APİ_KEY
    ): Response<NewsResponse>
}










