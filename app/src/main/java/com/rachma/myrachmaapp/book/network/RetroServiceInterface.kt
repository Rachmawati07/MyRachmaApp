package com.rachma.myrachmaapp.book.network

import com.rachma.myrachmaapp.book.models.BookListModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

// Untuk mendeklarasikan interface yang bernama RetroServiceInterface
interface RetroServiceInterface {

    // Untuk mendeklarasikan method yang bernama get yang berisikan value volumes
    @GET("volumes")
    // Untuk mendeklarasikan fungsi yang bernama getBookList dan mendeklarasikan prameter query yang mempunyao value q dengan tipe data string
    fun getBookList(@Query("q") query: String): Observable<BookListModel>

}