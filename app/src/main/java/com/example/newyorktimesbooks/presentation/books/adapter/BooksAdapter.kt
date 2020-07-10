package com.example.newyorktimesbooks.presentation.books.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder.from(parent)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(books[position])
    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.txtTitle
        private val author = itemView.txtAuthor

        fun bind(book: Book) {
            title.text = book.title
            author.text = book.author
        }

        companion object {
            fun from(parent: ViewGroup): BooksViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_book, parent, false)
                return BooksViewHolder(view)
            }
        }
    }
}