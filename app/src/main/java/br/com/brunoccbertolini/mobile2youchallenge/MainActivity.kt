package br.com.brunoccbertolini.mobile2youchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.brunoccbertolini.domain.util.Resource
import br.com.brunoccbertolini.mobile2youchallenge.databinding.ActivityMainBinding
import br.com.brunoccbertolini.myapplication.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(1500) //Be removed!
        setTheme(R.style.Theme_Mobile2YouChallenge)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesViewModel.getMoviesNowPlaying()
        inscribeObservers()

    }

    private fun inscribeObservers() {
        moviesViewModel.nowPlayingLiveData.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { cocktailResponse ->
                        binding.tvTest.text = cocktailResponse.toString()
                        Log.e("Main", "Success")
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e("Main", "An Error Occured $message")

                    }
                }

                is Resource.Loading -> {
                    Log.e("Main", "Loading....")

                }
            }

        })
    }
}