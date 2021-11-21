package br.com.brunoccbertolini.mobile2youchallenge.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.brunoccbertolini.domain.model.MovieListItem
import br.com.brunoccbertolini.mobile2youchallenge.databinding.ListMoviesItemBinding
import br.com.brunoccbertolini.mobile2youchallenge.util.Constants.Companion.BASE_IMG_URL
import com.bumptech.glide.Glide

class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<MovieListItem>() {
        override fun areItemsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean {
            return oldItem == newItem

        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListMoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bindView(movie)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((MovieListItem) -> Unit)? = null

    inner class ViewHolder(view: ListMoviesItemBinding) : RecyclerView.ViewHolder(view.root) {
        private val movieImage: ImageView = view.ivMovieImg


        fun bindView(movie: MovieListItem) {
            Log.e("RecyclerView", "bindView imag path: ${movie.poster_path}")
            movie.apply {

                Glide.with(itemView.context)
                    .load("$BASE_IMG_URL$poster_path")
                    .into(movieImage)
                itemView.setOnClickListener {
                    onItemClickListener?.let { it(movie) }
                }

            }
        }
    }

    fun setOnItemClickListener(listener: (MovieListItem) -> Unit) {
        onItemClickListener = listener
    }
}