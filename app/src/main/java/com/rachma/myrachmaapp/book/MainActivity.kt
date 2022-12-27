package com.rachma.myrachmaapp.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rachma.myrachmaapp.book.adapter.BookListAdapter
import com.rachma.myrachmaapp.book.models.BookListModel
import com.rachma.myrachmaapp.book.viewmodel.MainActivityViewModel
import com.rachma.myrachmaapp.databinding.ActivityMainBinding

// Untuk mendeklarasikan class yang bernama MainActivity
class MainActivity : AppCompatActivity() {

    // Untuk mendeklarasikan variabel TAG yang bernilai MY_TAG
    private val TAG = "MY_TAG"
    // Untuk mendeklarasikan variabel yang bernama binding dengan class berupa ActivityMainBinding
    private lateinit var binding: ActivityMainBinding
    // Untuk mendeklarasikan variabel yang bernama viewModel dengan class yang berupa MainActivityViewModel
    lateinit var viewModel: MainActivityViewModel
    // Untuk mendeklarasikan variabel bookListAdapter dengan class yang berupa BookListAdapter
    lateinit var bookListAdapter: BookListAdapter

    // Untuk memanggil fungsi onCreate
    // Dan memanggil layout ActivityMainBinding dengan binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSearchBox()
        initRecyclerView()
    }

    // Untuk mendeklarasikan fungsi yang bernama initSearchBox
    private fun initSearchBox() {

        // Untuk mendeklarasikan variabel binding
        binding.tiEtBookName.addTextChangedListener(object : TextWatcher {
            // Untuk memanggil fungsi yang bernama onTextChaged untuk mewakili urutan nilai Char yang dapat dibaca
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s != null) {
                    // Jika value parameter s tidak kosong, maka akan meload data dari api dan menjadikannya string
                    if (s.isNotEmpty()) {
                        loadDataFromAPI(s.toString())
                    }
                }
            }

            // Untuk memanggil fungsi beforeTextChanged
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            // Untuk memanggil fungsi afterTextChanged
            override fun afterTextChanged(p0: Editable?) {}
        })

        // Untuk mendeklarasikan variabel yang bernama binding
        binding.tiBookName.setEndIconOnClickListener {
            binding.tiEtBookName.setText("")
            bookListAdapter.setBookList(null)
            bookListAdapter.notifyDataSetChanged()
        }
    }

    // Untuk mendeklarasikan fungsi initRecyclerView
    private fun initRecyclerView(){
        // Untuk memanggil binding untuk menampilkan booklist
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
            bookListAdapter = BookListAdapter()
            adapter = bookListAdapter
        }
    }

    // Untuk mendeklarasikan fungsi loadDataFromApi
    private fun loadDataFromAPI(query: String) {
        // Untuk mendeklarasikan variabel viewmodel
        // Untuk membuat ViewModelProvider. Ini akan membuat ViewModels dan mempertahankannya di penyimpanan ViewModelStoreOwner yang diberikan
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        // Untuk mendeklarasikan viewmodel yang mendapatkan listbuku dari observe
        viewModel.getBookListObserver().observe(this, Observer<BookListModel>{
            // Jika item tidak sama dengan null maka akan ditampilkan daftar buku
            if(it.items != null){
                bookListAdapter.setBookList(it.items)
                bookListAdapter.notifyDataSetChanged()
            }
            // Jika sebaliknya, maka terdapat pesan error
            else{
                Toast.makeText(this, "Error in fetching books", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "loadDataFromAPI: it.items == null", )
            }
        })
        // Untuk mendeklarasikan variabel viewModel untuk memanggil api
        viewModel.makeAPICall(query)
    }
}