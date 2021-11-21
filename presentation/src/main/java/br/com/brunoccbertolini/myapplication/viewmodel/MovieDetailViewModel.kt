package br.com.brunoccbertolini.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import br.com.brunoccbertolini.domain.usecase.GetMovieDetailsUseCaseImpl
import br.com.brunoccbertolini.domain.usecase.GetMovieReviewUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val detailsUseCaseImpl: GetMovieDetailsUseCaseImpl,
    private val reviewUseCaseImpl: GetMovieReviewUseCaseImpl
): ViewModel() {


}