package com.rachma.myrachmaapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rachma.myrachmaapp.R
import com.rachma.myrachmaapp.adapters.NewsAdapter
import com.rachma.myrachmaapp.data.models.Article
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved_news.*

// Untuk mendeklarasikan class yang bernama savedNewsFragment
// Dan menampilkan layout yang berupa fragment_saved_news
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news), NewsAdapter.OnClickListener {
    // Untuk mendeklarasikan variabel yang bernama viewModel yang berisi class NewsViewModel
    lateinit var viewModel: NewsViewModel
    // Untuk mendeklarasikan variabel yang bernama newsAdapter yang berisi class NewsAdapter
    lateinit var newsAdapter: NewsAdapter

    // Untuk mendeklarasikan fungsi yang bernama onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Untuk mendeklarasikan variabel viewModel yang berupa activity sebagai NewsActivity
        viewModel = (activity as NewsActivity).viewModel

        // Untuk mendeklarasikan variabel yang berupa newsAdapter
        // Untuk memanggil recycler view untuk menampilkan rvSavedNews
        newsAdapter = NewsAdapter(this)
        rvSavedNews.apply {
            adapter = newsAdapter
            rvSavedNews.layoutManager = LinearLayoutManager(activity)
        }

        // Untuk mendeklarasikan variabel yang bernama itemTouchHelper
        // Yang digunakan untuk menghapus artikel apabila di geser ke kanan atau kiri
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            // Untuk memanggil fungsi onMove
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Untuk memanggil fungsi onSwipped
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Untuk mendeklarasikan variabel position
                val position = viewHolder.adapterPosition
                // Untuk mendeklarasikan variabel article
                val article = newsAdapter.differ.currentList[position]
                // Untuk mendeklarasikan variabel viewModel untuk menghapus artikel
                viewModel.deleteArticle(article)
                // Dan jika berhasil maka akan menampilkan pesan
                Snackbar.make(view, "Article deleted successfully", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        // Membuat ItemTouchHelper yang akan berfungsi dengan Callback yang diberikan.
        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(rvSavedNews)
        }


        // Untuk mendeklarasikan variabel viewModel untuk mendapatkan semua artikel yang disimpan
        // Untuk menambahkan observe tertentu ke daftar observe dalam masa tertentu. Dan akan dikirim di utas utama.
        // Jika LiveData sudah memiliki kumpulan data, itu akan dikirimkan ke observe
        viewModel.getAllSavedArticles().observe(viewLifecycleOwner, Observer { articles ->

            // Untuk mendeklarasikan variabel NewsAdapter.differ yang akan menghitung perbedaan antara daftar baru dan daftar lama lalu perbarui recyclerView
            newsAdapter.differ.submitList(articles)
        })


    }

    // Untuk memanggil fungsi onArticleItemClickListener dengan value parameter berupa article
    override fun onArticleItemClickListener(article: Article) {
        // Untuk mendeklarasikan variabel berupa bundle
        // Dan meletakan serializable yang berupa article
        val bundle = Bundle().apply {
            putSerializable("article", article)
        }

        // Untuk menemukan NavController yang diberikan Fragment
        // Dan memanggil ini pada Fragmen yang bukan NavHostFragment atau di dalam NavHostFragment makan akan menghasilkan IllegalStateException
        findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment, bundle)
    }
}