package com.example.newyorktimesbooks.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newyorktimesbooks.R
import com.example.newyorktimesbooks.domain.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(private val onClickListener: OnclickListener) :
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    private var books: List<Book> = mutableListOf()

    fun setList(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder.from(parent)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = books[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(book)
        }
        holder.bind(book)
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

class OnclickListener(private val block: (Book) -> Unit) {
    fun onClick(book: Book) = block(book)
}