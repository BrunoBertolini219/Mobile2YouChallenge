package br.com.brunoccbertolini.mobile2youchallenge.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.brunoccbertolini.mobile2youchallenge.databinding.FragmentMovieDetailsBinding
import br.com.brunoccbertolini.mobile2youchallenge.util.ConnectionLiveData
import br.com.brunoccbertolini.myapplication.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _viewBinding: FragmentMovieDetailsBinding? = null
    private val viewBinding: FragmentMovieDetailsBinding get() = _viewBinding!!

    private val viewModelMovieDetails: MovieDetailViewModel by viewModels()

    private lateinit var connectionLiveData: ConnectionLiveData
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackButton()
        checkInternetConnection()

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

            } else {
                Toast.makeText(
                    requireContext(),
                    "No Internet Connection Available!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}
