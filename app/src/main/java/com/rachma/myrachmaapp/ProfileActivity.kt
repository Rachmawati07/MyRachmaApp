package com.rachma.myrachmaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rachma.myrachmaapp.databinding.ActivityProfileBinding

// Untuk mendeklarasikan kelas yang bernama ProfileActivity
// Dan untuk menginisialisasi variabel binding untuk ActivityProfileBinding
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Untuk memanggil ActivityProfileBinding dengan menggunakan binding
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Untuk memberikan fungsi klik listener pada buttonHome agar ketika diklik bisa diarahkan pada fungsi home
        binding.buttonHome.setOnClickListener { home() }
    }

    // Untuk mendeklarasikan fungsi yang bernama home
    private fun home(){

        // Untuk mendeklarasikan variabel berupa intentHome dari kelas Intent
        // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(ProfileActivity menuju ke kelas HomeActivity)
        val intentHome = Intent(this, HomeActivity::class.java)

        // Untuk menjalankan activity melalui intent yang telah dideklarasikan
        startActivity(intentHome)
    }
}