package com.rachma.myrachmaapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rachma.myrachmaapp.data.repository.NewsRepository

// Untuk mendeklarasikan class yang bernama NewsViewModelProviderFactory
class NewsViewModelProviderFactory(
    // Untuk mendeklarasikan variabel yang bernama app yang berisi Application
    val app: Application,
    // Untuk mendeklarasikan variabel yang bernama newsrepository
    val newsRepository: NewsRepository
): ViewModelProvider.Factory {
    // Untuk memanggil fungsi dari view model untuk membuat kelas modellClass
    // Dan mengembalikan NewsViewModel yang berupa app dan newsRepository
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository) as T
    }
}