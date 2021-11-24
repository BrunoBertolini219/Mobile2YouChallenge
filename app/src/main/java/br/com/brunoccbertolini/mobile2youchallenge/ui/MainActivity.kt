package br.com.brunoccbertolini.mobile2youchallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.brunoccbertolini.mobile2youchallenge.R
import br.com.brunoccbertolini.mobile2youchallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1500)
        setTheme(R.style.Theme_Mobile2YouChallenge)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}