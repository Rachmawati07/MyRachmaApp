package com.rachma.myrachmaapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rachma.myrachmaapp.data.models.Source
import java.io.Serializable

// TODO 9 : Baris kode berikut merupakan bagian Entity(field)
// Untuk mendeklarasikan entitas dengan nama tabel berupa articles
@Entity(tableName = "articles")
// Untuk mendeklarasikan kelas data dengan nama Article
data class Article(
    // Untuk mendeklarasikan primary key dengan generate secara otomatis
    @PrimaryKey(autoGenerate = true)
    // Untuk mendeklarasikan variabel yang bernama id dengan tipe data berupa int
    var id: Int? = null,
    // Untuk mendeklarasikan variabel yang bernama author dengan tipe data string
    val author: String,
    // Untuk mendeklarasikan variabel yang bernama content dengan tipe data string
    val content: String,
    // Untuk mendeklarasikan variabel yang bernama desription dengan tipe data string
    val description: String,
    // Untuk mendeklarasikan variabel yang bernama publishedAt dengan tipe data string
    val publishedAt: String,
    // Untuk mendeklarasikan variabel yang bernama source dengan tipe data string
    val source: Source,
    // Untuk mendeklarasikan variabel yang bernama title dengan tipe data string
    val title: String,
    // Untuk mendeklarasikan variabel yang bernama url dengan tipe data string
    val url: String,
    // Untuk mendeklarasikan variabel yang bernama urlToImage dengan tipe data string
    val urlToImage: String
) : Serializable