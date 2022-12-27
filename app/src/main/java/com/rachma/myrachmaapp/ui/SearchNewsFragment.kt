package com.rachma.myrachmaapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rachma.myrachmaapp.R
import com.rachma.myrachmaapp.adapters.NewsAdapter
import com.rachma.myrachmaapp.data.models.Article
import com.rachma.myrachmaapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Untuk mendeklarasikan class yang bernama SearchNewsFragment
// Untuk menampilkan layout yang berupa fragement_search_News
class SearchNewsFragment : Fragment(R.layout.fragment_search_news), NewsAdapter.OnClickListener {

    // Untuk mendeklarasikan variabel yang bernama viewModel yang berisi class NewsViewModel
    lateinit var viewModel: NewsViewModel
    // Untuk mendeklarasikan variabel yang bernama newsAdapter yang berisi class NewsAdapter
    lateinit var newsAdapter: NewsAdapter
    // Untuk mendeklarasikan variabel yang bernama Tag yang berisi searchNewsFragment
    val TAG = "searchNewsFragment"

    // Untuk mendeklarasikan fungsi yang bernama onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Untuk mendeklarasikan variabel viewModel yang berupa activity sebagai NewsActivity
        viewModel = (activity as NewsActivity).viewModel

        // Untuk mendeklarasikan variabel yang berupa newsAdapter
        // Untuk memanggil recycler view untuk menampilkan rvSearchNews
        newsAdapter = NewsAdapter(this)
        rvSearchNews.apply {
            adapter = newsAdapter
            rvSearchNews.layoutManager = LinearLayoutManager(activity)
        }

        // Untuk mendeklarasikan variabel job
        // Meluncurkan coroutine baru tanpa memblokir thread saat ini
        // Dan mengembalikan referensi ke coroutine sebagai Job. Coroutine dibatalkan saat job yang dihasilkan dibatalkan.
        var job: Job? = null
        etSearch.addTextChangedListener { text ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                text?.let {
                    // Jika saat mengembalikan representasi string dari objek tidak kosong
                    // Maka akan menguba teks menjadi string
                    if (text.toString().isNotEmpty()) {
                        viewModel.searchNews(text.toString())
                    }
                }
            }
        }

        // Untuk mendeklarasikan variabel viewModel dan breakingNewsMultableLivedata
        // Untuk menambahkan observe tertentu ke daftar observe dalam masa tertentu. Dan akan dikirim di utas utama.
        // Jika LiveData sudah memiliki kumpulan data, itu akan dikirimkan ke observe
        viewModel.searchNewsMutableLiveData.observe(viewLifecycleOwner, Observer { response ->
            // Ketika respons pemanggilan api sukses
            // Maka akan menghentikan dan menyembunyikan efek kilau
            // Dan akan menampilkan respon yang berupa article
            when (response) {
                is Resource.Success -> {
                    shimmer_containerr.stopShimmerAnimation()
                    shimmer_containerr.visibility = View.GONE
                    response.data?.let { newsResponse ->
                        Log.d(TAG, "response: ${response.data}")
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                // Dan ketika pemanggilan api gagal
                // Maka aken menghentikan dan menyembunyiakan efek kilau
                // Dan akan menampilkan respon berupa pesan error
                is Resource.Error -> {//when the api call is failed
                    //stop and hide shimmer effect
                    shimmer_containerr.stopShimmerAnimation()
                    shimmer_containerr.visibility = View.GONE
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
                    //show shimmer effect
                    shimmer_containerr.startShimmerAnimation()
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
        findNavController().navigate(R.id.action_searchNewsFragment_to_articleFragment, bundle)
    }
}