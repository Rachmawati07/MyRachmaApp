package com.rachma.myrachmaapp.util

// Untuk mendeklarasikan class yang bernama Resource
sealed class Resource<T>(
    // Untuk mendeklarasikan variabel yang bernama data dan bernilai null
    val data: T? = null,
    // Untuk mendeklarasikan variabel yang bernama message yang mempunyai tipe data string dan bernilai null
    val message: String? = null
) {
    // Untuk mendeklarasikan class success untuk menerima data
    class Success<T>(data: T): Resource<T>(data)
    // Untuk mendeklarasikan class error untuk menerima pesan yang berupa message dengan tipe data string dan data yang bernilai null
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    // Untuk mendeklarasikan class loading
    class Loading<T> : Resource<T>()
}