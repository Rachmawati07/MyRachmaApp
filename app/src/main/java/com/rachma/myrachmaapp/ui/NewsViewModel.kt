package com.rachma.myrachmaapp.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rachma.myrachmaapp.MyRachmaApp
import com.rachma.myrachmaapp.data.models.Article
import com.rachma.myrachmaapp.data.models.NewsResponse
import com.rachma.myrachmaapp.data.repository.NewsRepository
import com.rachma.myrachmaapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

// TODO 2 : Kode berikut adalah bagian ViewModel yang terdapat live data
// Untuk mendeklarasikan class yang bernama NewsViewModel
class NewsViewModel(
    // Untuk mendeklarasikan value parameter yang berupa app
    app: Application,
    // Untuk mendeklarasikan variabel yang bernama newsRepository yang berisi class NewsRepository
    val newsRepository: NewsRepository
) : AndroidViewModel(app) {

    // Untuk mendeklarasikan variabel yang bernama breaking NewsMutableLiveData
    val breakingNewsMutableLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    // Untuk mendeklarasikan variabel yang bernama breakingNewsPage yang berisi value yaitu 1
    var breakingNewsPage = 1

    // Untuk mendeklarasikan variabel yang bernama searchNewsMutableLiveData
    val searchNewsMutableLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    // Untuk mendeklarasikan variabel yang bernama searchNewsPage yang berisi value yaitu 1
    var searchNewsPage = 1

    // Untuk menginisialisai fungsi getBreakingNews dengan countryCode yang berupa us
    init {
        getBreakingNews("us")
    }

    // Untuk mendeklarasikan fungsi yang bernama getBreakingNews dengan value parameter yang bertipe data string
    // Meluncurkan coroutine baru tanpa memblokir thread saat ini dan mengembalikan referensi ke coroutine sebagai Job.
    // Dan coroutine dibatalkan saat job yang dihasilkan dibatalkan.
    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        safeBreakingNewsCAll(countryCode)
    }

    // Untuk mendeklarasikan fungsi yang bernama searchNews dengan value parameter yang bertipe data string
    // Meluncurkan coroutine baru tanpa memblokir thread saat ini dan mengembalikan referensi ke coroutine sebagai Job.
    // Dan coroutine dibatalkan saat job yang dihasilkan dibatalkan.
    fun searchNews(searchQuery: String) = viewModelScope.launch {
        safeSearchNewsCAll(searchQuery)
    }

    // Mendeklarasikan fungsi handleBreakNewsResponse dengan akses private dan value parameter berupa response
    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        // Jika respon adalah sukses maka akan mengembalikan hasil respon sukses
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        // Dan jika gagal maka akan mengembalikan pesan error
        return Resource.Error(response.message())
    }

    // Mendeklarasikan fungsi handleSearchNewsResponse dengan akses private dan value parameter berupa response
    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        // Jika respon adalah sukses maka akan mengembalikan hasil respon sukses
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        // Dan jika gagal maka akan mengembalikan pesan error
        return Resource.Error(response.message())
    }

    // Untuk mendeklarasikan fungsi saveArticle
    // Meluncurkan coroutine baru tanpa memblokir thread saat ini dan mengembalikan referensi ke coroutine sebagai Job.
    // Dan coroutine dibatalkan saat job yang dihasilkan dibatalkan.
    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    // Untuk mendeklarasikan fungsi getAllSavedArticle
    // Yang merupakan sama dengan value pada variabel newsRepository yang mendapatkan semua artikel
    fun getAllSavedArticles() = newsRepository.getAllArticles()

    // Untuk mendeklarasikan fungsi yang bernama deleteArticle
    // Meluncurkan coroutine baru tanpa memblokir thread saat ini dan mengembalikan referensi ke coroutine sebagai Job.
    // Dan coroutine dibatalkan saat job yang dihasilkan dibatalkan.
    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

    // Untuk mendeklarasikan fungsi safeBreakingNewsCall dengan value parameter countryCode yang bertipe data string
    private suspend fun safeBreakingNewsCAll(countryCode: String) {
        // Untuk memposting tugas ke utas utama untuk menetapkan nilai yang diberikan. Jadi, jika  memiliki kode yang ingin dieksekusi, maka  dijalankan di utas utama:
        breakingNewsMutableLiveData.postValue(Resource.Loading())
        // Menggunakan methode try catch untuk mengeksekusi program
        try {
            // Jika mempunyai koneksi internet, maka akan ditampilkan respon berupa artikel dalam breakingnews
            if (hasInternetConnection()) {
                val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
                breakingNewsMutableLiveData.postValue(handleBreakingNewsResponse(response))
            // Dan jika tidak ada internet maka akan menampilkan pesan kesalahan berupa no intenet connection
            } else {
                breakingNewsMutableLiveData.postValue(Resource.Error("NO Internet Connection"))
            }
        // Menggunakan method catch untuk menangkap kesalahan
        // Dan akan ditampilkan pesan kesalahan berupa network failure
        } catch (t: Throwable) {
            when (t) {
                is IOException -> breakingNewsMutableLiveData.postValue(Resource.Error("Network Failure"))
            }
        }
    }

    // Untuk mendeklarasikan fungsi safeSearchNewsCall dengan value parameter searchQuery yang bertipe data string
    private suspend fun safeSearchNewsCAll(searchQuery: String) {
        // Untuk memposting tugas ke utas utama untuk menetapkan nilai yang diberikan. Jadi, jika  memiliki kode yang ingin dieksekusi, maka  dijalankan di utas utama
        searchNewsMutableLiveData.postValue(Resource.Loading())
        // Menggunakan methode try catch untuk mengeksekusi program
        try {
            // Jika mempunyai koneksi internet maka akan ditampilkan respons berupa searchNewsPage
            if (hasInternetConnection()) {
                val response = newsRepository.searchNews(searchQuery, searchNewsPage)
                searchNewsMutableLiveData.postValue(handleSearchNewsResponse(response))
            // Dan jika tidak ada koneksi internet, maka akan ditampilkan pesan kesalahan berupa no internet connection
            } else {
                searchNewsMutableLiveData.postValue(Resource.Error("NO Internet Connection"))
            }
        // Menggunakan methode catch untuk menangkap kesalahan
        // Dan akan ditampilkan pesan kesalahan berupa network failure
        } catch (t: Throwable) {
            when (t) {
                is IOException -> searchNewsMutableLiveData.postValue(Resource.Error("Network Failure"))
            }
        }
    }

    // Untuk mendeklarasikan fungsi yang bernama hasInternetConnection dengan tipe data boolean
    private fun hasInternetConnection(): Boolean {
        // Untuk mendeklarasikan variabel connectivityManager untuk mendapatkan aplikasi
        val connectivityManager = getApplication<MyRachmaApp>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        // Jika versi build sdk_int lebih besar sama dengan dari versi kode build m
        // Maka akan mendeklarasikan variabel activeNetwork
        // Dan akan mengembalikan false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetWork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetWork) ?: return false

            // Akan mengembalikan hasil aoabila koneksi wifi, celullar, ethernet bernilai true
            // Dan jika salah maka akan menghasilkan false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        // Jika versi build sdk_int tidak lebih besar dari versi kode build m, maka akan menghasilkan nilai false
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_ETHERNET -> true
                    TYPE_MOBILE -> true
                    else -> false
                }
            }
        }
        // Untuk mengembalikan nilai false
        return false
    }
}
