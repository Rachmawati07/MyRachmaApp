package com.rachma.myrachmaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachma.myrachmaapp.R
import com.rachma.myrachmaapp.data.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

// TODO 3 : Baris kode berikut merupakan bagian adapter untuk menampilkan data ke recycler view
// Untuk mendeklarasikan class yang bernama NewsAdapter
// Dan mendeklarasikan variabel onClickListener
class NewsAdapter (private val onClickListener: OnClickListener):
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    // Untuk memanggil fungsi onCreateView dengan value parameter berupa viewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        // Untuk mengembalikan ArticleViewHolder
        return ArticleViewHolder(
            // Untuk memanggil layout item_article_preview
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false)
        )
    }

    // Untuk memanggil fungsi getItemCount
    override fun getItemCount(): Int {
        // Untuk mengembalikan nilai dari differ
        return differ.currentList.size
    }

    // Untuk memanggil fungsi onBindViewHolder dengan value parameter berupa holder dan position yang bertipe data int
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        // Untuk mendeklarasikan variabel article dengan nilai differ dan daftar dari position
        val article = differ.currentList[position]
        holder.itemView.apply {
            // Untuk menjalankan library glide untuk meload gambar dan menjadikannya imageView
            Glide.with(this).load(article.urlToImage).into(article_image_iv)
            // Untuk mendapatkan datasource_tv, title_tv, description_tv, dan published_at_tv
            source_tv.text = article.source.name
            title_tv.text = article.title
            description_tv.text = article.description
            published_at_tv.text = article.publishedAt
            // Dan ketika diklik akan memanggil article
            setOnClickListener{
                onClickListener.onArticleItemClickListener(article)
            }
        }
    }

    // Untuk mendeklarasikan variabel differCallBack
    // Untuk membandingkan daftar baru dan yang lama dan hanya perbarui item yang diubah
    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        // Untuk memanggil fungsi areContentsTheSame
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            // Untuk mengembalikan item yang lama diganti dengan yang baru
            return oldItem == newItem
        }
    }

    // Untuk mendeklarasikan variabel differ
    val differ = AsyncListDiffer(this, differCallBack)

    // Untuk mendeklarasikan class ArticleViewHolder dengan value parameter berupa ItemView
    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    // Untuk mendeklarasikan interface OnClickListener
    // Dan menjalankan fungsi OnArticleItemClickListener
    interface OnClickListener {
        fun onArticleItemClickListener(article: Article)
    }
}