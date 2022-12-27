package com.rachma.myrachmaapp.book.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rachma.myrachmaapp.book.models.BookListModel
import com.rachma.myrachmaapp.book.network.RetroInstance
import com.rachma.myrachmaapp.book.network.RetroServiceInterface
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

// Untuk mendeklarasikan class yang bernama MainActivityViewModel
class MainActivityViewModel: ViewModel() {

    // Untuk mendeklarasikan variabel TAG dengan value berupa MY_TAG
    private val TAG = "MY_TAG"
    // Untuk mendeklarasikan variabel booklist untuk memanggil bookListModel
    lateinit var bookList: MutableLiveData<BookListModel>

    // Untuk menginisialisasikan variabel bookList
    init {
        bookList = MutableLiveData()
    }

    // Untuk mendeklarasikan fungsi yang bernama getBookListObserver
    fun getBookListObserver(): MutableLiveData<BookListModel>{
        // Untuk mengembalikan booklist
        return bookList
    }

    // Intuk mendeklarasikan fungsi yang memanggil API
    fun makeAPICall(query: String){

        // Untuk mendeklarasikan variabel restroInstance
        val retroInstance = RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)

        // Untuk mendapatkan list buku
        retroInstance.getBookList(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBookListObserverRx())
    }

    // Untuk mendeklarasikan fungsi getBookListObserverRx dengan hak akses privat
    private fun getBookListObserverRx(): Observer<BookListModel>{

        // Untuk mengembalikan objek dan memanggil fungsi onSubscribe
        return object : Observer<BookListModel>{
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe() called ")
            }

            // Untuk mamanggil fungsi onNext
            // Dan menampilkan pesan berupa onNext() called
            override fun onNext(t: BookListModel) {
                Log.d(TAG, "onNext() called ")
                bookList.postValue(t)
            }

            // Untuk mamanggil fungsi onError
            // Dan menampilkan pesan berupa onError() called
            override fun onError(e: Throwable) {
                Log.d(TAG, "onError() called: ${e.localizedMessage} ")
                bookList.postValue(null)
            }

            // Untuk memanggil fungsi onComple
            // Dan menampilkan pesan berupa onComplete() called
            override fun onComplete() {
                Log.d(TAG, "onComplete() called ")
            }
        }
    }
}