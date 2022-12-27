package com.rachma.myrachmaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rachma.myrachmaapp.R
import com.rachma.myrachmaapp.data.db.ArticleDatabase
import com.rachma.myrachmaapp.data.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_news.*

// Untuk mendeklarasikan class yang bernama NewsActivity
class NewsActivity : AppCompatActivity() {
    // Untuk mendeklarasikan variabel yang bernama viewModel
    lateinit var viewModel: NewsViewModel

    // Untuk memanggil fungsi onCreateView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Untuk mendeklarasikan layoutnya yaitu activity_news
        setContentView(R.layout.activity_news)

        // Untuk mendeklarasikan variabel yang bernama newsRespository yang berisikan objek articleDatabase dan menjalankan fungsi getDatabaseInstance
        val newsRepository = NewsRepository(ArticleDatabase.getDatabaseInstance(this))
        // Untuk mendeklarasikan variabel yang bernama viewModelProviderFactory
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        // Untuk mengakses viewModel pada ViewModelProvider dan mendapatkan Class newsviewmodel
        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(NewsViewModel::class.java)

        bottomNavigationView.setupWithNavController(nav_host_fragment.findNavController())
    }
}