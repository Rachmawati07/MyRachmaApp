package com.rachma.myrachmaapp

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.rachma.myrachmaapp.book.MainActivity
import com.rachma.myrachmaapp.databinding.ActivityHomeBinding
import com.rachma.myrachmaapp.ui.NewsActivity

// Baris berikut merupakan bagian view untuk data binding
// Untuk mendeklarasikan class yang bernama HomeActivity
class HomeActivity : AppCompatActivity() {

    // Untuk menginisialisasi variable binding
    private lateinit var binding: ActivityHomeBinding

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Untuk memanggil ActivityHomeBinding dengan menggunakan binding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Untuk memberikan fungsi klik listener pada btnReadnews agar ketika diklik bisa diarahkan pada fungsi news
        binding.btnReadnews.setOnClickListener { news() }

        // Untuk memberikan fungsi klik listener pada btnBook agar ketika diklik bisa diarahkan pada fungsi book
        binding.btnBook.setOnClickListener { book() }

        // Untuk memberikan fungsi klik listener pada btnProfile agar ketika diklik bisa diarahkan pada fungsi profile
        binding.btnProfile.setOnClickListener { profile() }

    }

    // Untuk mendeklarasikan fungsi yang bernama news
    private fun news(){

        // Untuk mendeklarasikan variabel berupa intentNews dari kelas Intent
        // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(HomeActivity menuju ke kelas NewsActivity)
        val intentNews = Intent(this, NewsActivity::class.java)

        // Untuk menjalankan activity melalui intent yang telah dideklarasikan
        startActivity(intentNews)
    }

    // Untuk mendeklarasikan fungsi yang bernama book
    private fun book(){

        // Untuk mendeklarasikan variabel berupa intentBook dari kelas Intent
        // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(HomeActivity menuju ke kelas MainActivity)
        val intentBook = Intent(this, MainActivity::class.java)

        // Untuk menjalankan activity melalui intent yang telah dideklarasikan
        startActivity(intentBook)
    }

    // Untuk mendeklarasikan fungsi yang bernama profile
    private fun profile(){

        // Untuk mendeklarasikan variabel berupa intentProfile dari kelas Intent
        // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(HomeActivity menuju ke kelas ProfileActivity)
        val intentProfile = Intent(this, ProfileActivity::class.java)

        // Untuk menjalankan activity melalui intent yang telah dideklarasikan
        startActivity(intentProfile)
    }
}