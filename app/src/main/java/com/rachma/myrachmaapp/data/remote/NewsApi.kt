package com.rachma.myrachmaapp.data.remote

import com.rachma.myrachmaapp.data.models.NewsResponse
import com.rachma.myrachmaapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top_headlines?country=us&apiKey=80c2e0be242c4772a3fb66327464e527
// Untuk mendeklarasikan interface yang bernama NewsApi
interface NewsApi {
    // Untuk mendeklarasikan method get yang berisi value berupa url
    @GET("v2/top-headlines")
    // Untuk mendeklarasikan fungsi yang bernama getBreakingNews
    suspend fun getBreakingNews(
        // Untuk mendeklarasikan parameter query yang berisikan value country
        @Query("country")
        // Untuk mendeklarasikan value parameter countryCode dengan tipe data string dan bernilai us
        countryCode: String = "us",
        // Untuk mendeklarasikan parameter query yang berisikan value page
        @Query("page")
        // Untuk mendeklarasikan value parameter pageNumber dengan tipe data int dan bernilai 1
        pageNumber : Int = 1,
        // Untuk mendeklarasikan parameter query yang berisikan value apiKey
        @Query("apiKey")
        // Untuk mendeklarasikan value parameter apiKey dengan tipe data string yang berisi Api key
        apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>

//https://newsapi.org/v2/everything?q=android&apiKey=80c2e0be242c4772a3fb66327464e527
    // Untuk mendeklarasikan method get yang berisi value berupa url
    @GET("v2/everything")
    // Untuk mendeklarasikan fungsi yang bernama searchForNews
    suspend fun searchForNews(
        // Untuk mendeklarasikan parameter query yang berisikan value q
        @Query("q")
        // Untuk mendeklarasikan value parameter searchQuery dengan tipe data string
        searchQuery: String,
        // Untuk mendeklarasikan parameter query yang berisikan value page
        @Query("page")
        // Untuk mendeklarasikan value parameter pageNumber dengan tipe data int
        pageNumber : Int = 1,
        // Untuk mendeklarasikan parameter query yang berisikan value apiKey
        @Query("apiKey")
        // Untuk mendeklarasikan value parameter apiKey dengan tipe data string yang berisi Api key
        apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>
}