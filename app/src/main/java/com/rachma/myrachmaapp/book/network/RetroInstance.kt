package com.rachma.myrachmaapp.book.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
// Untuk mendeklarasikan class yang bernama RetroInstance
class RetroInstance {

    // Untuk mendeklarasikan objek
    companion object{
    // https://www.googleapis.com/books/v1/volumes?q=jungle

        // Untuk mendeklarasikan variabel base_url yang berisi url untuk mengakses bukuapi
        val BASE_URL = "https://www.googleapis.com/books/v1/"

        // Untuk mendeklarasikan fungsi yang bernama getRetrofitInstance
        fun getRetroInstance(): Retrofit{

            // Untuk menjalankan library retrofit untuk mengakses api
            // Menggunakan method Builder Untuk mengakses url dan membuat GsonConverterFactory
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }

}