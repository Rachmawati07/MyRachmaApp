package com.rachma.myrachmaapp.data.db

import android.content.Context
import androidx.room.*
import com.rachma.myrachmaapp.data.models.Article

// Untuk mendeklaasikan database dengan entity yang berupa array dari class Article dan mempunyai versi berupa 1
@Database(entities = [Article::class], version = 5)
@TypeConverters(Converter::class)
// Untuk mendeklarasikan class yang berupa ArticleDatabase untuk RoomDatabase
abstract class ArticleDatabase : RoomDatabase() {

    // Untuk mendeklaraskan fungsi getArticleDao
    abstract fun getArticleDao(): ArticleDao

    companion object {
        // Untuk mencegah beberapa instance database dibuka pada saat yang bersamaan
        @Volatile
        // Untuk mendeklarasikan variabel yang bernama Instance pada ArticleDatabase yang berisi null
        private var Instance: ArticleDatabase? = null

        // Untuk mendeklarasikan fungsi yang bernama getDatabaseInstance
        fun getDatabaseInstance(context: Context): ArticleDatabase {

            // Jika Instance bukan null, maka akan dikembalikan
            // Dan jika null, maka akan dibuat databasenya yang bernama article_db.db
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_db.db"
                ).fallbackToDestructiveMigration().build()
                Instance = instance

                // Untuk mengembalikan instance
                instance
            }
        }
    }
}