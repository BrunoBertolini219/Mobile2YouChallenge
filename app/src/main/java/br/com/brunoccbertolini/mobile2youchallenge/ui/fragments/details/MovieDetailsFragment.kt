package br.com.brunoccbertolini.mobile2youchallenge.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.brunoccbertolini.domain.model.MovieDetailResponse
import br.com.brunoccbertolini.domain.model.MovieListItem
import br.com.brunoccbertolini.domain.util.Resource
import br.com.brunoccbertolini.mobile2youchallenge.databinding.FragmentMovieDetailsBinding
import br.com.brunoccbertolini.mobile2youchallenge.ui.adapters.MovieReviewsAdapter
import br.com.brunoccbertolini.mobile2youchallenge.ui.adapters.MoviesListAdapter
import br.com.brunoccbertolini.mobile2youchallenge.util.ConnectionLiveData
import br.com.brunoccbertolini.mobile2youchallenge.util.Constants.Companion.BASE_IMG_URL
import br.com.brunoccbertolini.mobile2youchallenge.util.Converter.Companion.genresListToString
import br.com.brunoccbertolini.mobile2youchallenge.util.Converter.Companion.minutesToHour
import br.com.brunoccbertolini.mobile2youchallenge.util.Converter.Companion.rateToVolteAvegare
import br.com.brunoccbertolini.myapplication.viewmodel.MovieDetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _viewBinding: FragmentMovieDetailsBinding? = null
    private val viewBinding: FragmentMovieDetailsBinding get() = _viewBinding!!


    val args: MovieDetailsFragmentArgs by navArgs()
    lateinit var navigationMovie: MovieListItem

    private lateinit var connectionLiveData: ConnectionLiveData
    private val viewModelMovieDetails: MovieDetailViewModel by viewModels()

    private lateinit var adapterMovies: MoviesListAdapter
    private lateinit var adapterMovieReviews: MovieReviewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationMovie = args.movie
        setupRecyclerViewReviews()
        setupRecyclerViewMovies()

        checkInternetConnection()
        setupNavigation()
        setupBackButton()

        setupMovieReviewObserve()
        setupMovieDetailsObserve()
        setupSimilarMoviesObserve()
    }

    private fun setupRecyclerViewMovies() {
        adapterMovies = MoviesListAdapter()
        viewBinding.rvMoviesSimilar.apply {
            adapter = adapterMovies
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                this.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun setupRecyclerViewReviews() {
        adapterMovieReviews = MovieReviewsAdapter()
        viewBinding.includeCvReviews.commentList.apply {
            adapter = adapterMovieReviews
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                this.context
            )
        }
    }

    private fun setupMovieDetailsObserve() {
        viewModelMovieDetails.movieDetailLiveData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        bindViewDetail(it)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
            }
        })
    }

    private fun setupSimilarMoviesObserve() {
        viewModelMovieDetails.similarMoviesLiveData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        adapterMovies.differ.submitList(it.results)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
            }
        })
    }

    private fun setupMovieReviewObserve() {
        viewModelMovieDetails.movieReviewLiveData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        adapterMovieReviews.differ.submitList(it.results)
                        }
                    }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
            }
        })
    }

    private fun bindViewDetail(movie: MovieDetailResponse) {
        viewBinding.apply {
            includeCvHeader.headerTitle.text = movie.title
            includeCvHeader.headerGenres.text = genresListToString(movie.genres)
            includeCvHeader.headerVotes.text = rateToVolteAvegare(movie.vote_average ?: 0f)
            includeCvHeader.headerTime.text = minutesToHour(movie.runtime ?: 0)
            tvMovieDetailSinopseContent.text = movie.overview

            Glide.with(this@MovieDetailsFragment).apply {
                load("$BASE_IMG_URL${movie.backdrop_path}").into(includeCvHeader.headerBackPoster)
                load("$BASE_IMG_URL${movie.poster_path}").into(includeCvHeader.headerPoster)
            }
        }
    }
    private fun setupBackButton() {
        viewBinding.includeCvHeader.headerBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun checkInternetConnection() {
        connectionLiveData = ConnectionLiveData(this.requireContext())
        connectionLiveData.observe(viewLifecycleOwner, { isAvailable ->
            if (isAvailable) {
                viewModelMovieDetails.getMovieDetail(navigationMovie.id ?: 1)
                viewModelMovieDetails.getMovieReview(navigationMovie.id ?: 1)
                viewModelMovieDetails.getSimilarMovies(navigationMovie.id?: 1)
            } else {
                Toast.makeText(
                    requireContext(),
                    "No Internet Connection Available!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun setupNavigation() {
        adapterMovies.setOnItemClickListener {
            viewModelMovieDetails.getMovieDetail(it.id ?: 1)
            viewModelMovieDetails.getMovieReview(it.id ?: 1)
            viewModelMovieDetails.getSimilarMovies(it.id ?:1)
            setupMovieDetailsObserve()
            setupSimilarMoviesObserve()
            setupMovieReviewObserve()
        }
    }

    private fun hideProgressBar() {
        viewBinding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        viewBinding.paginationProgressBar.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}
