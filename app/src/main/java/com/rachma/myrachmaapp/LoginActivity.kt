package com.rachma.myrachmaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rachma.myrachmaapp.databinding.ActivityLoginBinding

// Untuk mendeklarasikan class yang bernama Login Activity
class LoginActivity : AppCompatActivity() {

    // Untuk menginisialisasi variable binding
    private lateinit var binding: ActivityLoginBinding

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Untuk memanggil ActivityLoginBinding dengan menggunakan binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Untuk memberikan fungsi klik listener pada buttonLogin agar ketika diklik bisa diarahkan pada fungsi cekPassword
        binding.buttonLogin.setOnClickListener { cekPassword() }
    }

    // Untuk mendeklarasikan fungsi yang bernama cekPassword
    private fun cekPassword() {

        // Untuk mengambil data dari variabel nama pengguna dan passwordPengguna
        // Dan kemudian dikonversi dari teks menjadi string
        val namaPengguna = binding.textUsername.text.toString()
        val passwordPengguna = binding.textPassword.text.toString()

        // Untuk mengecek pada namaPengguna dan passwordPengguna apakah kosong atau tidak
        // Jika kosong maka akan ditampilkan popup berupa tulisan Mohon Masukkan username dan password
        if (namaPengguna.isEmpty() || passwordPengguna.isEmpty()) {
            Toast.makeText(this, "Mohon Masukkan Username dan Password", Toast.LENGTH_SHORT).show()
            return
        }

        // Untuk mengecek apakah pada isian data untuk namaPengguna diisikan berupa Rachma dan untuk passwordPengguna berupa Fatamorgana
        // Jika benar maka akan melanjutkan program selanjutnya untuk dieksekusi, namun jika salah akan menjalankan program else
        if(namaPengguna == "Rachma" && passwordPengguna == "Fatamorgana"){

            // Untuk mendeklarasikan variabel berupa intent dari kelas Intent
            // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(MainActivity menuju ke kelas HomeActivity)
            val intent = Intent(this, HomeActivity::class.java)

            // Pada variabel intent ditambahkan data dengan kunci berupa username
            // nilainya diambil dari inputan username
            // Dan kemudian dikonversi dari teks menjadi string
            intent.putExtra("Username", binding.textUsername.text.toString())

            // Untuk menjalankan activity melalui intent yang telah dideklarasikan
            startActivity(intent)

            // Untuk mendeklarasikan fungsi finish() yang berarti ketika intent selesai dijalankan maka MainActivity dihapus dari backstack
            finish()

            // Program yang dijalankan ketika fungsi if salah, ketika username dan password tidak sesuai yang dideklarasikan
            // Yang berarti untuk menampilkan popup berupa tulisan Username atau password salah
        }else{
            Toast.makeText(this, "Username atau Password salah.", Toast.LENGTH_SHORT).show()
            return
        }
    }
}