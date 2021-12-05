package br.com.brunoccbertolini.mobile2youchallenge.ui.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.brunoccbertolini.domain.model.MovieListCategory
import br.com.brunoccbertolini.domain.model.MovieListItem
import br.com.brunoccbertolini.domain.model.MoviesList
import br.com.brunoccbertolini.domain.util.Resource
import br.com.brunoccbertolini.mobile2youchallenge.R
import br.com.brunoccbertolini.mobile2youchallenge.databinding.FragmentMoviesBinding
import br.com.brunoccbertolini.mobile2youchallenge.ui.adapters.MoviesParentAdapter
import br.com.brunoccbertolini.mobile2youchallenge.ui.adapters.OnMainItemClickListener
import br.com.brunoccbertolini.mobile2youchallenge.util.ConnectionLiveData
import br.com.brunoccbertolini.mobile2youchallenge.util.SectionDiffUtil
import br.com.brunoccbertolini.myapplication.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), OnMainItemClickListener {

    private var _viewBinding: FragmentMoviesBinding? = null
    private val viewBinding: FragmentMoviesBinding get() = _viewBinding!!

    private val viewModelMovies: MoviesViewModel by viewModels()

    private lateinit var connectionLiveData: ConnectionLiveData
    private var populateListMovies = mutableListOf<MovieListCategory>()

    private lateinit var adapterMoviesMovies: MoviesParentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _viewBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        populateListMovies.clear()
        checkInternetConnection()
        setupRecyclerViewAdapter()
        return viewBinding.root
    }


    private fun setupRecyclerViewAdapter() {

        val difUtil = SectionDiffUtil()
        adapterMoviesMovies = MoviesParentAdapter(difUtil, requireContext(), this)
        viewBinding.apply {
            rvMoviesList.layoutManager = LinearLayoutManager(requireContext())
            rvMoviesList.adapter = adapterMoviesMovies
        }

    }

    private fun setupNowPlayingObservers() {
        viewModelMovies.nowPlayingLiveData.observe(viewLifecycleOwner, { res ->
            val response = res.peekContent()
            val movieTitle = getString(R.string.em_exibicao)
            checkResourceResponse(response, movieTitle)
        })
    }

    private fun setupUpcomingObservers() {
        viewModelMovies.upcomingLiveData.observe(viewLifecycleOwner, { res ->
            val response = res.peekContent()
            val movieTitle = getString(R.string.em_breve)
            checkResourceResponse(response, movieTitle)
        })
    }

    private fun setupPopularObservers() {
        viewModelMovies.popularLiveData.observe(viewLifecycleOwner, { res ->
            val response = res.peekContent()
            val movieTitle = getString(R.string.mais_populares)
            checkResourceResponse(response, movieTitle)
        })
    }

    private fun setupTopRatedObservers() {
        viewModelMovies.topRatedLiveData.observe(viewLifecycleOwner, { res ->
            val response = res.peekContent()
            val movieTitle = getString(R.string.melhores_avaliados)
            checkResourceResponse(response, movieTitle)
        })
    }

    private fun hideProgressBar() {
        viewBinding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        viewBinding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun checkInternetConnection() {
        connectionLiveData = ConnectionLiveData(this.requireContext())
        connectionLiveData.observe(viewLifecycleOwner, { isAvailable ->
            if (isAvailable) {
                setupNowPlayingObservers()
                setupUpcomingObservers()
                setupPopularObservers()
                setupTopRatedObservers()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_internet_connection),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        populateListMovies.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    private fun checkResourceResponse(response: Resource<MoviesList>, movieTitle: String) {
        when (response) {
            is Resource.Success -> {
                response.data?.let { moviesResponse ->
                    populateListMovies.add(
                        MovieListCategory(
                            movieTitle,
                            moviesResponse.results ?: emptyList()
                        )
                    )
                    adapterMoviesMovies.submitList(populateListMovies)
                    hideProgressBar()
                }
            }
            is Resource.Error -> {
                response.message?.let { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    hideProgressBar()
                }
            }
            is Resource.Loading -> {
                showProgressBar()
            }
        }
    }

    override fun onMainItemClick(movie: MovieListItem, position: Int) {

        val bundle = Bundle().apply {
            putSerializable("movie", movie)
        }
        findNavController().navigate(
            R.id.action_moviesFragment_to_detailsFragment,
            bundle
        )
    }
}