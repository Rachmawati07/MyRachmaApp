package com.rachma.myrachmaapp.data.models

import com.rachma.myrachmaapp.data.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)