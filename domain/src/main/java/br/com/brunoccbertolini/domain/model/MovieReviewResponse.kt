package br.com.brunoccbertolini.domain.model

data class MovieReviewResponse(
    val results: List<MovieReviewItem>
)

data class MovieReviewItem(
    val author: String?,
    val content: String?
)