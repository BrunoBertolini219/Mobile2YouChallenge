package br.com.brunoccbertolini.mobile2youchallenge.ui.fragments.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.brunoccbertolini.domain.util.Resource
import br.com.brunoccbertolini.mobile2youchallenge.R
import br.com.brunoccbertolini.mobile2youchallenge.databinding.FragmentMoviesBinding
import br.com.brunoccbertolini.mobile2youchallenge.ui.adapters.MoviesListAdapter
import br.com.brunoccbertolini.myapplication.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _viewBinding: FragmentMoviesBinding? = null
    private val viewBinding: FragmentMoviesBinding get() = _viewBinding!!

    private val viewModelMovies: MoviesViewModel by viewModels()

    private lateinit var adapterMovies: MoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         viewModelMovies.getMoviesNowPlaying()

        setupObservers()

        setupRecyclerView()
        setupNavigation()

    }

    private fun setupNavigation() {
        adapterMovies.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("drink", it)
            }
            findNavController().navigate(
                R.id.action_moviesFragment_to_detailsFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView() {
        adapterMovies = MoviesListAdapter()
        viewBinding.rvPlayingNowMovies.apply {
            adapter = adapterMovies
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                this@MoviesFragment.requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun setupObservers() {
        viewModelMovies.nowPlayingLiveData.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { moviesResponse ->
                        adapterMovies.differ.submitList(moviesResponse.results)
                        hideProgressBar()
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(requireContext(), message , Toast.LENGTH_SHORT).show()
                        hideProgressBar()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()

                }
            }
        })
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