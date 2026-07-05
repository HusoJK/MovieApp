package com.eduacation.movieapp.presentation.movieDetail

import com.eduacation.movieapp.domain.model.MovieDetail

data class MovieDetailsViewState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = "",
)