package com.rachma.myrachmaapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rachma.myrachmaapp.R
import com.rachma.myrachmaapp.adapters.NewsAdapter
import com.rachma.myrachmaapp.data.models.Article
import com.rachma.myrachmaapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

// Untuk mendeklarasikan class yang bernama BreakingNewsFragment
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news),
    NewsAdapter.OnClickListener {

    // Untuk mendeklarasikan variabel viewModel yang berisi class NewsViewModel
    lateinit var viewModel: NewsViewModel
    // Untuk mendeklarasikan variabel newsAdapter yang berisi class NewsAdapter
    lateinit var newsAdapter: NewsAdapter
    // Untuk mendeklarasikan variabel TAG yang berisi value BreakingNewsFragment
    val TAG = "BreakingNewsFragment"

    // Untuk memanggil fungsi onViewCreated yang berisikan value parameter view dan savedInstanceState
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Untuk mendeklarasikan variabel viewModel dan memanggil activity sebagai newsActivity
        viewModel = (activity as NewsActivity).viewModel

        // Untuk mendeklarasikan newsAdapter
        // Untuk memanggil recycler view untuk menampilkan rvBreakingNews
        newsAdapter = NewsAdapter(this)
        rvBreakingNews.apply {
            adapter = newsAdapter
            rvBreakingNews.layoutManager = LinearLayoutManager(activity)
        }

        // Untuk mendeklarasikan variabel viewModel dan breakingNewsMultableLivedata
        // Untuk menambahkan observe tertentu ke daftar observe dalam masa tertentu. Dan akan dikirim di utas utama.
        // Jika LiveData sudah memiliki kumpulan data, itu akan dikirimkan ke observe
        viewModel.breakingNewsMutableLiveData.observe(viewLifecycleOwner, Observer { response ->
            // Ketika respons pemanggilan api sukses
            // Maka akan menghentikan dan menyembunyikan efek kilau
            // Dan akan menampilkan respon yang berupa article
            when (response) {
                is Resource.Success -> {
                    shimmer_container.stopShimmerAnimation()
                    shimmer_container.visibility = View.GONE
                    response.data?.let { newsResponse ->
                        Log.d(TAG, "response: ${response.data}")
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                // Dan ketika pemanggilan api gagal
                // Maka aken menghentikan dan menyembunyiakan efek kilau
                // Dan akan menampilkan respon berupa pesan error
                is Resource.Error -> {
                    shimmer_container.stopShimmerAnimation()
                    shimmer_container.visibility = View.GONE
                    response.message?.let { errorMessage ->
                        Toast.makeText(
                            activity,
                            "An Error Occurred: $errorMessage",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                // Dan ketika pemanggilan api sedang loading
                // Maka akan ditampilkan efek kilau
                is Resource.Loading -> {
                    shimmer_container.startShimmerAnimation()
                }
            }
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
        findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment, bundle)
    }
}