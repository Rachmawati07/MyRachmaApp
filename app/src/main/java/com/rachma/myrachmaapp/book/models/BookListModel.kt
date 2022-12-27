package com.rachma.myrachmaapp.book.models

// Untuk mendeklarasikan kelas data yang bernama BookListModel yang berisikan variabel yang bernama items yang merupakan list array
data class BookListModel(val items: ArrayList<Items>?)
// Untuk mendeklarasikan kelas data yang bernama items yang berisi variabel yang bernama volumeInfo
data class Items(val volumeInfo: VolumeInfo?)
// Untuk mendeklarasikan kelas data yang bernama VolumeInfo yang berisi variabel title yang bertipe data string, mendeklarasikan variabel yang bernama authors yang merupakan arraylist,
// mendeklarasikan variabel yang bernama description yang merupakan string, mendeklarasikan variabel yan bernama imagelinks
data class VolumeInfo(val title: String?, val authors: ArrayList<String>?, val description: String?, val imageLinks: ImageLinks?)
// Untuk mendeklarasikan kelas data yang bernama imageLinks yang berisi variabel yang bernama smallThumbnail yang mempunyai tipe data string
data class ImageLinks(val smallThumbnail: String?)