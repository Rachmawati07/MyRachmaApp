package com.rachma.myrachmaapp.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.rachma.myrachmaapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

// Untuk mendeklarasikan class yang bernama ArticleFragment
class ArticleFragment : Fragment(R.layout.fragment_article) {
    // Untuk mendeklarasikan variabel yang bernama viewModel
    lateinit var viewModel: NewsViewModel
    // Untuk mendeklarasikan variabel yang bernama args yang mengembalikan lazy untuk mengakses argumen Fragmen sebagai instance Args.
    val args: ArticleFragmentArgs by navArgs()

    // Untuk memanggil fungsi onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        // Untuk mendeklarasikan variabel yang bernama article
        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        // Untuk menyimpan article kedalam room database
        // Dan akan menampilkan pesan berupa article saved successfully
        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved Successfully",Snackbar.LENGTH_SHORT).show()
        }
    }
}