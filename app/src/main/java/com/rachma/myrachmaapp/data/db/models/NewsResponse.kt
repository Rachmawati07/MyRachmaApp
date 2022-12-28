package com.rachma.myrachmaapp.data.models

import com.rachma.myrachmaapp.data.models.Article

// Baris kode berikut merupakan Bagian Class Data yang merupakan objek data
// Untuk mendeklarasikan kelas data yang bernama NewsResponse
data class NewsResponse(
    // Untuk mendeklarasikan variabel yang bernama articles
    val articles: List<Article>,
    // Untuk mendeklarasikan variabel yang bernama status dengan tipe data string
    val status: String,
    // Untuk mendeklarasikan variabel yang bernama totalResult dengan tipe data int
    val totalResults: Int
)