package br.com.brunoccbertolini.mobile2youchallenge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.brunoccbertolini.domain.model.MovieListItem
import br.com.brunoccbertolini.mobile2youchallenge.databinding.ListMoviesItemBinding
import br.com.brunoccbertolini.mobile2youchallenge.util.Constants.Companion.BASE_IMG_URL
import com.bumptech.glide.Glide

class MoviesChildAdapter(
    diffUtil: DiffUtil.ItemCallback<MovieListItem>
) : ListAdapter<MovieListItem, MoviesChildAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListMoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bindView(movie)

    }

    private var onItemClickListener: ((MovieListItem) -> Unit)? = null

    inner class ViewHolder(view: ListMoviesItemBinding) : RecyclerView.ViewHolder(view.root),
        View.OnClickListener {
        val binding = ListMoviesItemBinding.bind(itemView)
        private val movieImage = binding.ivMovieImg
        fun bindView(movie: MovieListItem) {
            movie.apply {
                Glide.with(itemView.context)
                    .load("$BASE_IMG_URL$poster_path")
                    .into(movieImage)

                itemView.setOnClickListener {
                    onItemClickListener?.let { it(movie) }
                }

            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

        }


    }

    fun setOnItemClickListener(listener: (MovieListItem) -> Unit) {
        onItemClickListener = listener
    }
}