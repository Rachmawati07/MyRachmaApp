package com.rachma.myrachmaapp.data.remote

import com.rachma.myrachmaapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Untuk mendeklarasikan class yang bernama RetrofitClient
class RetrofitClient {
    // Untuk mendeklarasikan objek
    companion object{
        // Untuk mendeklarasikan variabel yang bernama retrofit
        // lazy berarti apa yang ada di dalam tanda kurung ini hanya akan diinisialisasi sekali
        private val retrofit by lazy {
            // Untuk mendeklarasikan variabel yang bernama logging
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            // Untuk mendeklarasikan variabel yang bernama client
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            // Untuk menjalankan library retrofit untuk mengakses api
            // Menggunakan method Builder Untuk mengakses url dan membuat GsonConverterFactory
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        // Untuk mendeklarasikan variabel yang bernama api
        // Dengan memanggil library retrofit dan membuat kelas java NewsApi
        val api by lazy {
            retrofit.create(
                NewsApi::class.java)
        }
    }
}