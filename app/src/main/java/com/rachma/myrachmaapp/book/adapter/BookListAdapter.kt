package com.rachma.myrachmaapp.book.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachma.myrachmaapp.R
import com.rachma.myrachmaapp.book.models.BookListModel
import com.rachma.myrachmaapp.book.models.Items
import com.rachma.myrachmaapp.databinding.RowLayoutBinding

// Untuk mendeklarasikan class yang bernama BookListAdapter
// Dan memanggil RecyclerView untuk menampilkan buku
class BookListAdapter: RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {

    // Untuk mendeklarasikan variabel yang bernama booklist nilainya tersimpan pada daftar array
    private var bookList: ArrayList<Items>? = null

    // Untuk mendeklarasikan fungsi yang bernama setBookList dengan value parameter berupa booklist
    fun setBookList(bookList: ArrayList<Items>?){
        this.bookList = bookList
    }

    // Untuk memanggil fungsi onCreateViewHolder dengan value parameter berupa ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        // Untuk mendeklarasikan variabel yang bernama view dan menginflate layout
        val view = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // Untuk mengembalikan BookListViewHolder dan viewnya
        return BookListViewHolder(view)
    }

    // Untuk memanggil fungsi onBidViewHolder  dengan value parameter berupa holder dan position dengan tipe data int
    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(bookList?.get(position)!!)
    }

    // Untuk memanggil fungsi getItemCount
    // Untuk menghitung buku dan akan dikembalikan sebagai list buku
    override fun getItemCount(): Int {
        if(bookList?.size == null) return 0
        else return bookList?.size!!
    }

    // Untuk mendeklarasikan class yang bernama BookListViewHolder
    class BookListViewHolder(view: RowLayoutBinding): RecyclerView.ViewHolder(view.root){

        // Untuk mendeklarasikan variabel tvBookTitle, tvauthor, tvDescription, dan ivBookImage
        private val tvBookTitle = view.tvBookTitle
        private val tvAuthor = view.tvAuthor
        private val tvDescription = view.tvDescription
        private val ivBookImage = view.ivBookImage

        // Untuk mendeklarasikan fungsi binf yang berisi value parameter berupa data
        fun bind(data: Items){
            //  Untuk mendeklarasikan variabel tvBookTitle yang berupa text yang sebagai judul
            tvBookTitle.text = data.volumeInfo?.title
            //  Untuk mendeklarasikan variabel tvAuthor yang berupa text sebagai authors
            // Dan ketika tidak ada penulisnya maka akan ditampilkan teks berupa no authors
            tvAuthor.text = data.volumeInfo?.authors?.let {
                    TextUtils.join(", ", it)
            }?: "No authors"
            //  Untuk mendeklarasikan variabel tvDescription yang berupa text yang sebagai deskripsi
            // Dan ketika tidak ada deskripsi maka akan ditampilkan teks no description available
            tvDescription.text = data.volumeInfo?.description ?: "No description available"

            data.volumeInfo?.imageLinks?.smallThumbnail.let{
                // Menjalankan library glide untuk meload gambar dan menjadikannya ivBookImage
                Glide.with(ivBookImage)
                    .load(it)
                    .placeholder(R.drawable.image_not_available)
                    .circleCrop()
                    .into(ivBookImage)
            }
        }
    }
}