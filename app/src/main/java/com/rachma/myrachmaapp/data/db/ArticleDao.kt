package com.rachma.myrachmaapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rachma.myrachmaapp.data.models.Article

// Untuk mendeklarasikan Dao
@Dao
// Mendeklarasikan interface yang bernama ArticleDao
interface ArticleDao {
    // Untuk mendeklarasikan method insert yang jika menambahkan artikel yang sudah ada, maka akan digantikan
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // Untuk menjalankan fungsi upsert
    suspend fun upsert(article: Article): Long

    // Untuk menjalankan query yang menyeleksi semua dara dari article
    @Query("SELECT * FROM articles")
    // Untuk menjalankan fungsi getAllArticles dengan livedata
    fun getAllArticles(): LiveData<List<Article>>

    // Untuk mendeklarasikan method delete
    // Untuk menjalankan fungsi deleteArticle
    @Delete
    suspend fun deleteArticle(article: Article)
}