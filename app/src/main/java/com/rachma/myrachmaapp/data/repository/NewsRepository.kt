package com.rachma.myrachmaapp.data.repository

import com.rachma.myrachmaapp.data.db.ArticleDatabase
import com.rachma.myrachmaapp.data.models.Article
import com.rachma.myrachmaapp.data.remote.RetrofitClient

// TODO 6 : Baris berikut adalah Bagian Repository
// Untuk mendeklarasikan class yang bernama NewsRepository
class NewsRepository(
    // Untuk mendeklarasikan variabel yang bernama db yang berisi articledatabase
    val db: ArticleDatabase
) {
    // Untuk mendeklarasikan fungsi yang bernama getBreakingNews yang berisi nilai countryCode yang bertipe data string dan pageNumber dengan tipe data int
    // Dan menggunakan library retrofit untuk mendapatkan countrycode dan pagenumber
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitClient.api.getBreakingNews(countryCode, pageNumber)

    // Untuk mendeklarasikan fungsi yang bernama searchNews yang berisi nilai searchQuery yang bertipe data string dan pageNumber dengan tipe data int
    // Dan menggunakan library retrofit untuk mendapatkan countrycode dan pagenumber
    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitClient.api.searchForNews(searchQuery, pageNumber)

    // Untuk mendeklarasikan fungsi yang bernama upsert yang berisi nilai article untuk mendapatkan articleDao dari db
    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    // Untukk mendeklarasikan fungsi getAllArticles untuk mendapatkan semua article dari db
    fun getAllArticles() = db.getArticleDao().getAllArticles()

    // Untukk mendeklarasikan fungsi deleteArticle untuk mendapatkan article dari db dan menjalankan fungsi deleteArticle
    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}