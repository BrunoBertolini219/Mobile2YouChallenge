package br.com.brunoccbertolini.mobile2youchallenge.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.brunoccbertolini.domain.model.MovieListCategory
import br.com.brunoccbertolini.mobile2youchallenge.databinding.ListMoviesBinding
import br.com.brunoccbertolini.mobile2youchallenge.util.UserDiffUtil

class MoviesParentAdapter(
    diffUtil: DiffUtil.ItemCallback<MovieListCategory>,
    private val context: Context,
    private val listener: OnMainItemClickListener
) :
    ListAdapter<MovieListCategory, MoviesParentAdapter.ViewHolder>(diffUtil) {

    val recycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }


    inner class ViewHolder(view: ListMoviesBinding) : RecyclerView.ViewHolder(view.root){
        val binding = ListMoviesBinding.bind(itemView)

        fun bind(movie: MovieListCategory) {
            binding.tvMoviesCategory.text = movie.category

            val childAdapter = MoviesChildAdapter(UserDiffUtil())
            childAdapter.submitList(movie.movieList)

            binding.rvMoviesCategory.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.rvMoviesCategory.adapter = childAdapter
            binding.rvMoviesCategory.setRecycledViewPool(recycledViewPool)
            childAdapter.setOnItemClickListener {
                listener.onMainItemClick(it, bindingAdapterPosition)

            }

        }
    }
}
