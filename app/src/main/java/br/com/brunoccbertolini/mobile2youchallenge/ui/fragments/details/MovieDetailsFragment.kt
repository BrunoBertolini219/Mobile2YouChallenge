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
import br.com.brunoccbertolini.mobile2youchallenge.R
import br.com.brunoccbertolini.mobile2youchallenge.databinding.FragmentMovieDetailsBinding
import br.com.brunoccbertolini.mobile2youchallenge.ui.adapters.MovieReviewsAdapter
import br.com.brunoccbertolini.mobile2youchallenge.ui.adapters.MoviesPlayingSimilar
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
    lateinit var argsMovieListItem: MovieListItem

    private lateinit var connectionLiveData: ConnectionLiveData
    private val viewModelMovieDetails: MovieDetailViewModel by viewModels()

    private lateinit var adapterMoviesSimilar: MoviesPlayingSimilar
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
        argsMovieListItem = args.movie
        setupRecyclerViewMovieReviews()
        setupRecyclerViewMovies()

        checkInternetConnection()
        setupSimilarMoviesNavigation()
        setupBackButton()

        setupMovieReviewsObserve()
        setupMovieDetailsObserve()
        setupSimilarMoviesObserve()
    }

    private fun setupRecyclerViewMovies() {
        adapterMoviesSimilar = MoviesPlayingSimilar()
        viewBinding.rvMoviesSimilar.apply {
            adapter = adapterMoviesSimilar
            layoutManager = LinearLayoutManager(
                this.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun setupRecyclerViewMovieReviews() {
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
                        bindViewMovieDetails(it)
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
                        adapterMoviesSimilar.differ.submitList(it.results)
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

    private fun setupMovieReviewsObserve() {
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

    private fun bindViewMovieDetails(movieDetails: MovieDetailResponse) {
        viewBinding.apply {
            includeCvHeader.headerTitle.text = movieDetails.title
            includeCvHeader.headerGenres.text = genresListToString(movieDetails.genres)
            includeCvHeader.headerVotes.text = rateToVolteAvegare(movieDetails.vote_average ?: 0f)
            includeCvHeader.headerTime.text = minutesToHour(movieDetails.runtime ?: 0)
            tvMovieDetailSinopseContent.text = movieDetails.overview

            Glide.with(this@MovieDetailsFragment).apply {
                load("$BASE_IMG_URL${movieDetails.backdrop_path}").into(includeCvHeader.headerBackPoster)
                load("$BASE_IMG_URL${movieDetails.poster_path}").into(includeCvHeader.headerPoster)
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
                viewModelMovieDetails.getMovieDetail(argsMovieListItem.id ?: 1)
                viewModelMovieDetails.getMovieReview(argsMovieListItem.id ?: 1)
                viewModelMovieDetails.getSimilarMovies(argsMovieListItem.id ?: 1)
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_internet_connection),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun setupSimilarMoviesNavigation() {
        adapterMoviesSimilar.setOnItemClickListener {
            viewModelMovieDetails.getMovieDetail(it.id ?: 1)
            viewModelMovieDetails.getMovieReview(it.id ?: 1)
            viewModelMovieDetails.getSimilarMovies(it.id ?: 1)
            setupMovieDetailsObserve()
            setupSimilarMoviesObserve()
            setupMovieReviewsObserve()
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
