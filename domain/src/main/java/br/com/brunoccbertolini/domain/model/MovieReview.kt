package br.com.brunoccbertolini.domain.model

import java.io.Serializable

data class MovieReview(
    val results: List<MovieReviewItem>
)

data class MovieReviewItem(
    val author: String?,
    val content: String?,
): Serializable