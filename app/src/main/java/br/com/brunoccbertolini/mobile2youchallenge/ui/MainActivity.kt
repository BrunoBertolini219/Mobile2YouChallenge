package br.com.brunoccbertolini.mobile2youchallenge.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import br.com.brunoccbertolini.mobile2youchallenge.R
import br.com.brunoccbertolini.mobile2youchallenge.databinding.ActivityMainBinding
import br.com.brunoccbertolini.myapplication.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1500) //Be removed!
        setTheme(R.style.Theme_Mobile2YouChallenge)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                onBackPressed()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}