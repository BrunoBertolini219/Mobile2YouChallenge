package br.com.brunoccbertolini.mobile2youchallenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.brunoccbertolini.domain.model.MovieReviewItem
import br.com.brunoccbertolini.mobile2youchallenge.databinding.ComentaryListItemBinding

class MovieReviewsAdapter : RecyclerView.Adapter<MovieReviewsAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<MovieReviewItem>() {
        override fun areItemsTheSame(oldItem: MovieReviewItem, newItem: MovieReviewItem): Boolean {
            return oldItem.author == oldItem.author
        }

        override fun areContentsTheSame(oldItem: MovieReviewItem, newItem: MovieReviewItem): Boolean {
            return oldItem == newItem

        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ComentaryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reviewItem = differ.currentList[position]
        holder.bindView(reviewItem)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(view: ComentaryListItemBinding) : RecyclerView.ViewHolder(view.root) {
        private val reviewTitle: TextView = view.itemCommentUsername
        private val reviewContent: TextView = view.itemComment


        fun bindView(review: MovieReviewItem) {
            review.apply {
                reviewTitle.text = author
                reviewContent.text = content
            }
        }
    }

}